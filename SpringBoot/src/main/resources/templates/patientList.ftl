<div class="generic-container">
    <div class="panel panel-default">
        <!-- Default panel contents -->
        <div class="panel-heading"><span class="lead">Adding patient to {{patientCtrl.instituteName}} </span></div>
		<div class="panel-body">
	        <div class="formcontainer">
	            <div class="alert alert-success" role="alert" ng-if="patientCtrl.successMessage">{{patientCtrl.successMessage}}</div>
	            <div class="alert alert-danger" role="alert" ng-if="patientCtrl.errorMessage">{{patientCtrl.errorMessage}}</div>
	            <form ng-submit="patientCtrl.submit()" name="myForm" class="form-horizontal">
	                <input type="hidden" ng-model="patientCtrl.Patient.id" />
	                <div class="row">
	                    <div class="form-group col-md-12">
	                        <label class="col-md-2 control-lable" for="name">Name</label>
	                        <div class="col-md-7">
	                            <input type="text" ng-model="patientCtrl.Patient.name" id="name" class="username form-control input-sm" placeholder="Enter Patient Name " required ng-minlength="3"/>
	                        </div>
	                    </div>
	                </div>

	                <div class="row">
	                    <div class="form-group col-md-12">
	                        <label class="col-md-2 control-lable" for="dateOfBirth">Date of Birth</label>
	                        <div class="col-md-7">
	                              <input type="date" id="dateOfBirth" ng-model="patientCtrl.Patient.dateOfBirth" required />
	                        </div>
	                    </div>
	                </div>
	                
	                <div class="row">
	                    <div class="form-group col-md-12">
	                        <label class="col-md-2 control-lable" for="gender">Gender</label>
	                        <div class="col-md-7">
	                            <input type="radio" id="gender" ng-model="patientCtrl.Patient.gender" ng-value="true"/ > Male
								<input type="radio" id="gender" ng-model="patientCtrl.Patient.gender" ng-value="false"/>  Female
	                        </div>
	                    </div>
	                </div>
	
	                <div class="row">
	                    <div class="form-actions floatRight">
	                        <input type="submit"  value="{{!patientCtrl.Patient.id ? 'Create' : 'Update'}}" class="btn btn-primary btn-sm" >
	                        <button type="button" ng-click="patientCtrl.reset()" class="btn btn-warning btn-sm" >Reset Form</button>
	                        <button type="button" ng-click="patientCtrl.goBack()" class="btn btn-warning btn-sm" >Back</button>
	                    </div>
	                </div>
	            </form>
    	    </div>
		</div>	
    </div>
    <div class="alert alert-success" role="alert" ng-if="patientCtrl.ageMessage">{{patientCtrl.ageMessage}}</div>
    <div class="panel panel-default">
        <!-- Default panel contents -->
        <div class="panel-heading"><span class="lead">List of Patients </span></div>
		<div class="panel-body">
			<div class="table-responsive">
		        <table class="table table-hover">
		            <thead>
		            <tr>
		                <th>ID</th>
		                <th>NAME</th>
		                <th>Date of Birth</th>
		                <th>Gender</th>
		                <th width="100"></th>
		                <th width="100"></th>
		                <th width="100"></th>
		            </tr>
		            </thead>
		            <tbody>
		            <tr ng-repeat="u in patientCtrl.getAllPatients()">
		                <td>{{u.id}}</td>
		                <td>{{u.name}}</td>
		                <td>{{patientCtrl.getDate(u.dateOfBirth)}}</td>
		                <td>{{u.gender ? 'Male' : 'Female'}}</td>
		                <td><button type="button" ng-click="patientCtrl.editPatient(u.id)" class="btn btn-success custom-width">Edit</button></td>
		                <td><button type="button" ng-click="patientCtrl.removePatient(u.id)" class="btn btn-danger custom-width">Remove</button></td>
		                <td><button type="button" ng-click="patientCtrl.getAge(u.dateOfBirth)" class="btn btn-warning btn-sm" >Get Age</button></td>
		                <td><button type="button" ng-click="patientCtrl.addExamination(u.name, u.id, patientCtrl.instituteId, patientCtrl.instituteName)" class="btn btn-warning btn-sm" >Add Examination</button></td>
		            </tr>
		            </tbody>
		        </table>		
			</div>
			
		</div>
    </div>
</div>