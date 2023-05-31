import { Component, Input, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'app-filter',
  templateUrl: './filter.component.html',
  styleUrls: ['./filter.component.css']
})
export class FilterComponent {

  @Input()
  filter: boolean;

  @Output()
  filterEvent = new EventEmitter();

  filterData = {
    toggle: false,
    filters: {
      fromPrice: null,
      toPrice: null,
      isNew: null,
      isGood: null,
      isUsed: null,
      age: null
    }
  };

  toggleFilter(): void {
    this.filterData.toggle = !this.filterData.toggle;
    this.filterEvent.emit(this.filterData);
  }

  applyFilter(): void {
    this.filterEvent.emit(this.filterData);
  }
  
  resetFilter() {
    this.filterData.filters = {
      fromPrice: null,
      toPrice: null,
      isNew: null,
      isGood: null,
      isUsed: null,
      age: null
    };

    // this.applyFilter();
  }  

}
