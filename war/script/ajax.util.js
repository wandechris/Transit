var HOME='home';
var ENTITY_CUSTOMER='transit';
var ENTITY_PRODUCT='product';
var ENTITY_ITEM='item';
var ENTITY_ORDER='order';

//function to initialize the page
var init = function() {
	//showing the home tab on initializing
	showTab(HOME);
	//adding event listeners to the tabs
	$('#tabs a').click(function(event) {
		showTab(event.currentTarget.id);
	});
}

//function to show the tab
var showTab = function(entity) {
	//remove the active class from all the tabs
	$('.tab').removeClass("active");
	//setting the active class to the selected tab
	$('#'+entity).addClass("active");
	//hiding all the tabs
	$('.g-unit').hide();
	//showing the selected tab
	$('#' + entity + '-tab').show();
	//hiding the message block
	$('.message').hide();
	//hiding the create block
	showHideCreate(entity, false);
	if(entity!=HOME)
		$('#'+entity+'-search-reset').click();
}

//function to show/hide create block for an entity in a tab 
var showHideCreate = function(entity, show) {
	//checking if the block is show or not
	if (show) {
		//hiding the search container
		$('#' + entity + '-search-ctr').hide();
		//hiding the list container
		$('#' + entity + '-list-ctr').hide();
		//showing the create container
		$('#' + entity + '-create-ctr').show();
		
	} else {
		//showing the search container
		$('#' + entity + '-search-ctr').show();
		//showing the list container
		$('#' + entity + '-list-ctr').show();
		//hiding the create container
		$('#' + entity + '-create-ctr').hide();
		//checking if the entity is not a home then populating the list of the entity
		if(entity!=HOME)
			populateList(entity,null);
	}
}

//parameter object definition
var param=function(name,value){
	this.name=name;
	this.value=value;
}

//function to add an entity when user clicks on the add button in UI
var add = function(entity) {
	$('.message').hide();
	$('#'+entity+'-reset').click();
	//display the create container
	showHideCreate(entity, true);
	$("span.readonly input").attr('readonly', false);
	$("select[id$=order-customer-list] > option").remove();
	$("select[id$=order-item-list] > option").remove();
	$("select[id$=item-product-list] > option").remove();
	//checking the entity to populate the select box
	if (entity == ENTITY_ITEM) {
		//populating the product and contact by making an ajax call
		populateSelectBox('item-product-list', '/product');
	}  else if (entity == ENTITY_ORDER){
		// populating the customer and item select box by making an ajax call
		populateSelectBox('order-customer-list','/customer');
		populateSelectBox('order-item-list','/item');
	}
}

//function to search an entity when user inputs the value in the search box
var search = function(entity) {
	$('.message').hide();
	// collecting the field values from the form
	 var formEleList = $('form#'+entity+'-search-form').serializeArray();
	 //assigning the filter criteria
	 var filterParam=new Array();
	 for(var i=0;i<formEleList.length;i++){
		 filterParam[filterParam.length]=new param(formEleList[i].name,formEleList[i].value); 
	 }
	 //calling population of the list through ajax
	 populateList(entity,filterParam);
}

var showMessage = function(message, entity){
	$('#'+entity+'-show-message').show().html('<p><b>'+message+'</b></p>');
}

var formValidate = function(entity){
	var key;
	var formEleList = $('form#'+entity+'-create-form').serializeArray();
	key=formEleList[0].value;
	switch(entity){
		case ENTITY_ITEM:
			var valueProduct = $('#item-product-list').val();
			if(valueProduct == "" || key == ""){
				showMessage('please check the key and Product values in the form', entity);
				return;
			}
			break;
		case ENTITY_ORDER:
			var valueCustomer=$('#order-customer-list').val();
			var valueItem = $('#order-item-list').val();
			if(valueCustomer == "" || valueItem==""){
				showMessage('please check the Customer and Item values in the form', entity);
				return;
			}
			break;
		case ENTITY_CUSTOMER:
			var hasError = false;
		    var emailReg = /^([\w-\.]+@([\w-]+\.)+[\w-]{2,4})?$/;
		    var emailaddressVal = $("#eMail").val();
		    if(emailaddressVal == '' || !emailReg.test(emailaddressVal)|| key=="") {
		      hasError = true;
		    }
		    if(hasError == true){
		    	showMessage('please check the name and email values in the form', entity);
				return;
		    }
			break;
		default :
			if(key==""){
				showMessage('please check the values in the form', entity);
				return;
			}
			break;
	}
	save(entity);
	$('#'+entity+'-show-message').hide();
}

//function to save an entity
var save = function(name, direction, adhere, lat, lon, upDate, currentLoc, date) {
		// creating the data object to be sent to backend
	 var data=new Array();
		data[data.length]=new param("name",name);
		data[data.length]=new param("adherence", adhere);
		data[data.length]=new param("direction", direction);
		data[data.length]=new param("latitude", lat);
		data[data.length]=new param("longitude", lon);
		data[data.length]=new param("msgTime", upDate);
		data[data.length]=new param("timepoint", currentLoc);
		data[data.length]=new param("date", date);
		
	 //setting action as PUT
	 data[data.length]=new param('action','PUT');
	 //making the ajax call
	 $.ajax({
			url : "/transit",
			type : "POST",
			data:data
		});
}

// function to get the data by setting url, filter, success function and error function
var getData=function(url,successFn,errorFn){
	// making the ajax call
	$.ajax({
		url : url,
		type : "GET",
		success : function(resp) {
			console.log(resp);
			//calling the user defined success function
			if(successFn)
			successFn(resp);	
		},
	error:function(e){
		//calling the user defined error function
		if(errorFn)
		 errorFn(e);
	}
	});
}


//function to populate the list of an entity
var populateList=function(entity, filter){
	//specifying the success function. When the ajax response is successful then the following function will be called

	var successFn=function(resp){
		var data='';
		if(resp){
			//getting the data from the response object
			data=resp.data;
		}
	}
	getData("/"+entity,filter,successFn,null);
}