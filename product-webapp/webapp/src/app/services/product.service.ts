import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, BehaviorSubject } from 'rxjs';
import { map } from 'rxjs/operators';
import { Product } from '../modals/product';
import { UserService } from './user.service';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  private productsSubject: BehaviorSubject<Product[]> = new BehaviorSubject<Product[]>([]);
  public products$: Observable<Product[]> = this.productsSubject.asObservable();

  URL = "http://localhost:9090";

  constructor(
    private http: HttpClient,
    private userService: UserService
  ) { }

  // fetch all products
  fetchProducts(): void {
    this.http.get<Product[]>(this.URL+"/products2").subscribe(
      (products: Product[]) => {
        this.productsSubject.next(products);
      },
      (error) => {
        console.error('Error fetching products:', error);
      }
    );
  }

  fetchProductsForUser(email: string): Observable<Product[]> {
    return this.http.get<Product[]>(this.URL+"/products/"+email);
  }

  fetchSellerForProduct(productId: string): Observable<any> {
    return this.http.get(this.URL+"/user/"+productId);
  }

  // add a new product to database 
  addProduct(product: any, images: string[]): Observable<boolean> {
    return new Observable<boolean>(observer => {
      this.userService.getUserEmail().subscribe(email => {
        product.email = email;
        product.images = images;

        const currentDate = new Date();
        const options: Intl.DateTimeFormatOptions = { month: 'short', day: '2-digit', year: 'numeric' };
        product.date = currentDate.toLocaleDateString('en-US', options).toUpperCase();

        console.log("Product top be added: ", product);
        
        this.http.post(this.URL+"/product", product).subscribe(
          () => {
            observer.next(true); // Request succeeded
            observer.complete();
          },
          () => {
            observer.next(false); // Request failed
            observer.complete();
          }
        );
      });
    });
  }  

  // returns a single product by id 
  getProductById(productId: string): Observable<Product> {
    console.log(productId);
    return this.products$.pipe(
      map(products => products.find(product => product.id == productId))
    );
  }

  // returns products which include the keyword in the title 
  getProductsByKeyword(keyword: string): Observable<Product[]> {
    console.log(keyword);
    return this.products$.pipe(
      map(products => {
        return products.filter(product => product.title.toLowerCase().includes(keyword.toLowerCase()));
      })
    );
  }

  // returns products based on category 
  getProductsByCategory(category: string): Observable<Product[]> {
    console.log(category);
    return this.products$.pipe(
      map(products => {
        return products.filter(product => product.category.toLowerCase().includes(category.toLowerCase()));
      })
    );
  }

}