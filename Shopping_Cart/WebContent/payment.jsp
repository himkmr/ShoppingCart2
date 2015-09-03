<!DOCTYPE html>
<html>
<head>
<jsp:include page="/header.jsp"/>
<title>Welcome to Himanshu's Shopping Cart</title>
</head>
<body>
<jsp:include page="/navbar.jsp"/>
<div align="center">
<br><br>



<br>
${message}
<br>
<br>
<form style="width:30%;"  class="form-horizontal" action="Makepayment" id="myform">
<div class="form-group" >
    <label class="control-label col-sm-4" for="name">Card Number</label>
    <div class="col-sm-8">
    <input type="text" class="form-control" name="cardnumber" placeholder="Card Number">
  </div></div>
  <div class="form-group">
    <label class="control-label col-sm-4" for="text">Expires On</label>
    <div class="col-sm-8">
    <input type="date" class="form-control" name="street" placeholder="date">
    </div></div>
<div class="form-group">
    <label class="control-label col-sm-10" for="text">Billing Address</label>
    </div>
<div class="form-group" >
    <label class="control-label col-sm-2" for="name">Name</label>
    <div class="col-sm-10">
    <input type="text" class="form-control" name="name" placeholder="Full Name">
  </div></div>
  <div class="form-group">
    <label class="control-label col-sm-2" for="text">Street</label>
    <div class="col-sm-10">
    <input type="text" class="form-control" name="street" placeholder="street">
  </div></div>
  <div class="form-group">
    <label class="control-label col-sm-2" for="text">City</label>
    <div class="col-sm-10">
    <input type="text" class="form-control" name="city" placeholder="city">
  </div></div>
  <div class="form-group">
    <label class="control-label col-sm-2" for="text">State</label>
    <div class="col-sm-10">
    <input type="text" class="form-control" name="state" placeholder="state">
  </div></div>
   <button type="submit" class="btn btn-default">Make Payment!</button>
</form>
</div>
</body>
</html>