<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Laila&family=Signika+Negative:wght@300&display=swap" rel="stylesheet">
    <title>Payment Notification</title>
    <style>

        .mainDiv{
            border: 2px solid black;
            border-radius: 5px;
            width: 50%;
            margin: 5% 25%;
            font-family: 'Laila', sans-serif;;
        }
        .header,.orderConfirm,.purchase,.paymentDetails,.OrderTotal{
            border-bottom: 2px solid grey;
        }
        .log_image{
            width: 25vh;
            height: 25vh;
        }
        .purchase{
            /*margin-left: 7px;*/
            text-align: center;
        }
        .paymentDetails{
            display: grid;
            grid-template-columns: 1fr 1fr;
        }
        .OrderTotal,.keepInTouch,.header,.orderConfirm,.paymentDetails{
            text-align: center;
        }
        .email-body{
            padding: 30px;
            line-height: 200%;
        }
    </style>
</head>
<body>
<div class="mainDiv">
    <div class="orderConfirm">
        <h1>Payment Confirmation</h1>
        <h3>your purchase has been placed for shipping </h3>
    </div>

    <div class="purchase">
        <!--    <h3>Your Product Order Details</h3>-->
        <!--    <h4>Name</h4>-->
        <!--    <p>${productName}</p>-->
        <!--    <h4>Title</h4>-->
        <!--    <p>${productTitle}</p>-->
        <!--    <h4>Description</h4>-->
        <!--    <p>${productDescription}</p>-->
        <!--    <h4>Category</h4>-->
        <!--    <p>${productCategory}</p>-->
        <!--    <h4>Condition</h4>-->
        <!--    <p>${productCondition}</p>-->
        <h2>Thank you for using swall sell</h2>

    </div>
    <div class="email-body">
        <p>We are pleased to inform you that your payment for the product/service has been successfully processed.
            Your payment confirmation ensures that you can now proceed to collect the product/service from the designated person or location.<br><br>
            Thank you for choosing our services, and we look forward to serving you again in the future.
            If you have any further questions or require assistance, please feel free to reach out to us.
    </div>

    <!--  <div class="paymentDetails">-->
    <!--    <div class="ShippingAddress">-->
    <!--      <p>Payee Email</p>-->
    <!--      <p>${payeeEmail}</p>-->
    <!--    </div>-->
    <!--    <div class="Details">-->
    <!--      <p>Amount paid</p>-->
    <!--      <p>	${amountPaid}</p>-->
    <!--    </div>-->
    <!--  </div>-->
    <!--  <div class="OrderTotal">-->
    <!--    <h3>Total amount paid</h3>-->
    <!--    <h1>${totalAmountPaid}</h1>-->
    <!--  </div>-->
    <div class="keepInTouch">
        <h3>Keep In Touch</h3>
        <p>If you have any questions, concerns, or suggestions,please email us:<a href="#">Support@swapsell.com</a></p>
    </div>
</div>
</body>
</html>





















<#--<!DOCTYPE html>-->
<#--<html lang="en">-->
<#--<head>-->
<#--    <meta charset="UTF-8">-->
<#--    <meta http-equiv="X-UA-Compatible" content="IE=edge">-->
<#--    <meta name="viewport" content="width=device-width, initial-scale=1.0">-->
<#--    <link rel="preconnect" href="https://fonts.googleapis.com">-->
<#--    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>-->
<#--    <link href="https://fonts.googleapis.com/css2?family=Laila&family=Signika+Negative:wght@300&display=swap" rel="stylesheet">-->
<#--    <title>Payment Notification</title>-->
<#--    <style>-->

<#--        .mainDiv {-->
<#--            border: 2px solid black;-->
<#--            border-radius: 5px;-->
<#--            width: 50%;-->
<#--            margin: 5% 25%;-->
<#--            font-family: 'Laila', sans-serif;;-->
<#--        }-->
<#--        .purchase{-->
<#--            margin-left: 7px;-->
<#--        }-->
<#--        .paymentDetails{-->
<#--            display: grid;-->
<#--            grid-template-columns: 1fr 1fr;-->
<#--        }-->
<#--        .OrderTotal,.keepInTouch,.orderConfirm,.paymentDetails{-->
<#--            text-align: center;-->
<#--        }-->
<#--    </style>-->
<#--</head>-->
<#--<body>-->
<#--    <div class="mainDiv">-->
<#--        <div class="orderConfirm">-->
<#--            <h1>Payment Confirmation</h1>-->
<#--            <h3>your purchase has been placed for shipping </h3>-->
<#--        </div>-->

<#--        <div class="purchase">-->
<#--            <h3>Your Product Order Details</h3>-->
<#--            <h4>Name</h4>-->
<#--            <p>${productName}</p>-->
<#--            <h4>Title</h4>-->
<#--            <p>${productTitle}</p>-->
<#--            <h4>Description</h4>-->
<#--            <p>${productDescription}</p>-->
<#--            <h4>Category</h4>-->
<#--            <p>${productCategory}</p>-->
<#--            <h4>Condition</h4>-->
<#--            <p>${productCondition}</p>-->
<#--        </div>-->
<#--        <div class="paymentDetails">-->
<#--            <div class="ShippingAddress">-->
<#--                <p>Payee Email</p>-->
<#--                <p>${payeeEmail}</p>-->
<#--            </div>-->
<#--            <div class="Details">-->
<#--                <p>Amount paid</p>-->
<#--                <p>	${amountPaid}</p>-->
<#--            </div>-->
<#--        </div>-->
<#--        <div class="OrderTotal">-->
<#--            <h3>Total amount paid</h3>-->
<#--            <h1>${totalAmountPaid}</h1>-->
<#--        </div>-->
<#--        <div class="keepInTouch">-->
<#--            <h3>Keep In Touch</h3>-->
<#--            <p>If you have any questions, concerns, or suggestions,please email us:<a href="#">swapsell@gmail.com</a></p>-->
<#--        </div>-->
<#--    </div>-->
<#--</body>-->
<#--</html>-->


