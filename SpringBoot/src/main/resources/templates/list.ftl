<div class="generic-container">
    <div class="panel panel-default">
        <!-- Default panel contents -->
        <div class="panel-heading"><span class="lead">Institution </span></div>
		<div class="panel-body">
	        <div class="formcontainer">
	            <div class="alert alert-success" role="alert" ng-if="ctrl.successMessage">{{ctrl.successMessage}}</div>
	            <div class="alert alert-danger" role="alert" ng-if="ctrl.errorMessage">{{ctrl.errorMessage}}</div>
	            <form ng-submit="ctrl.submit()" name="myForm" class="form-horizontal">
	                <input type="hidden" ng-model="ctrl.Institution.id" />
	                <div class="row">
	                    <div class="form-group col-md-12">
	                        <label class="col-md-2 control-lable" for="name">Name</label>
	                        <div class="col-md-7">
	                            <input type="text" ng-model="ctrl.Institution.name" id="name" class="username form-control input-sm" placeholder="Enter Institution Name " required ng-minlength="3"/>
	                        </div>
	                    </div>
	                </div>

	                <div class="row">
	                    <div class="form-group col-md-12">
	                        <label class="col-md-2 control-lable" for="description">Description</label>
	                        <div class="col-md-7">
	                            <input type="text" ng-model="ctrl.Institution.description" id="description" class="form-control input-sm" placeholder="Enter Description." required/>
	                        </div>
	                    </div>
	                </div>
	
	                <div class="row">
	                    <div class="form-actions floatRight">
	                        <input type="submit"  value="{{!ctrl.Institution.id ? 'Create' : 'Update'}}" class="btn btn-primary btn-sm" >
	                        <button type="button" ng-click="ctrl.reset()" class="btn btn-warning btn-sm" >Reset Form</button>
	                    </div>
	                </div>
	            </form>
    	    </div>
		</div>	
    </div>
    <div class="panel panel-default">
        <!-- Default panel contents -->
        <div class="panel-heading"><span class="lead">List of Institutions </span></div>
		<div class="panel-body">
			<div class="table-responsive">
		        <table class="table table-hover">
		            <thead>
		            <tr>
		                <th>ID</th>
		                <th>NAME</th>
		                <th>DESCRIPTION</th>
		                <th width="100"></th>
		                <th width="100"></th>
		                <th width="100"></th>
		            </tr>
		            </thead>
		            <tbody>
		            <tr ng-repeat="u in ctrl.getAllInstitutions()">
		                <td>{{u.id}}</td>
		                <td>{{u.name}}</td>
		                <td>{{u.description}}</td>
		                <td><button type="button" ng-click="ctrl.editInstitution(u.id)" class="btn btn-success custom-width">Edit</button></td>
		                <td><button type="button" ng-click="ctrl.removeInstitution(u.id)" class="btn btn-danger custom-width">Remove</button></td>
		                <td><button type="button" ng-click="ctrl.addPatient(u.name, u.id)" class="btn btn-warning btn-sm" >Add Patient</button></td>
		            </tr>
		            </tbody>
		        </table>		
			</div>
		</div>
    </div>
</div>