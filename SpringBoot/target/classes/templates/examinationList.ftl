<div class="generic-container">
    <div class="panel panel-default">
        <!-- Default panel contents -->
        <div class="panel-heading"><span class="lead">Adding examinations to {{examinationCtrl.patientName}}</span></div>
		<div class="panel-body">
	        <div class="formcontainer">
	            <div class="alert alert-success" role="alert" ng-if="examinationCtrl.successMessage">{{examinationCtrl.successMessage}}</div>
	            <div class="alert alert-danger" role="alert" ng-if="examinationCtrl.errorMessage">{{examinationCtrl.errorMessage}}</div>
	            <form ng-submit="examinationCtrl.submit()" name="myForm" class="form-horizontal">
	                <input type="hidden" ng-model="examinationCtrl.Examination.id" />
	                <div class="row">
	                    <div class="form-group col-md-12">
	                        <label class="col-md-2 control-lable" for="name">Name</label>
	                        <div class="col-md-7">
	                            <input type="text" ng-model="examinationCtrl.Examination.name" id="name" class="username form-control input-sm" placeholder="Enter Examination Name " required ng-minlength="3"/>
	                        </div>
	                    </div>
	                </div>

	                <div class="row">
	                    <div class="form-group col-md-12">
	                        <label class="col-md-2 control-lable" for="examDate">Date of Examination</label>
	                        <div class="col-md-7">
	                              <input type="date" id="examDate" ng-model="examinationCtrl.Examination.examDate" required />
	                        </div>
	                    </div>
	                </div>
	                
	                   <div class="row">
	                    <div class="form-group col-md-12">
	                        <label class="col-md-2 control-lable" for="description">Description</label>
	                        <div class="col-md-7">
	                            <input type="text" ng-model="examinationCtrl.Examination.description" id="description" class="form-control input-sm" placeholder="Enter Description."  required/>
	                        </div>
	                    </div>
	                </div>
	                
	                   <div class="row">
	                    <div class="form-group col-md-12">
	                        <label class="col-md-2 control-lable" for="description">Height (In Meter.)</label>
	                        <div class="col-md-7">
	                            <input type="number" ng-model="examinationCtrl.Examination.height" id="height" class="form-control input-sm" ng-pattern="/^[0-9]+(\.[0-9]{1,2})?$/" step="0.01"  placeholder="Enter Height in Meter."required/>
	                        </div>
	                    </div>
	                </div>
	                
	                   <div class="row">
	                    <div class="form-group col-md-12">
	                        <label class="col-md-2 control-lable" for="description">Weight (In Kg.)</label>
	                        <div class="col-md-7">
	                            <input type="number" ng-model="examinationCtrl.Examination.weight" id="weight" class="form-control input-sm" ng-pattern="/^[0-9]+(\.[0-9]{1,2})?$/"  step="0.01" placeholder="Enter Weight in Kg."  required/>
	                        </div>
	                    </div>
	                </div>
	                
	                <div class="row">
	                    <div class="form-actions floatRight">
	                        <input type="submit"  value="{{!examinationCtrl.Examination.id ? 'Create' : 'Update'}}" class="btn btn-primary btn-sm" >
	                        <button type="button" ng-click="examinationCtrl.reset()" class="btn btn-warning btn-sm" >Reset Form</button>
	                        <button type="button" ng-click="examinationCtrl.goBack()" class="btn btn-warning btn-sm" >Back</button>
	                    </div>
	                </div>
	            </form>
    	    </div>
		</div>	
    </div>
    <div class="alert alert-success" role="alert" ng-if="examinationCtrl.ageMessage">{{examinationCtrl.ageMessage}}</div>
    <div class="panel panel-default">
        <!-- Default panel contents -->
        <div class="panel-heading"><span class="lead">List of Examinations </span></div>
		<div class="panel-body">
			<div class="table-responsive">
		        <table class="table table-hover">
		            <thead>
		            <tr>
		                <th>ID</th>
		                <th>NAME</th>
		                <th>ExamDate</th>
		                <th>Description</th>
		                 <th>Height</th>
		                <th>Weight</th>
		                <th width="100"></th>
		                <th width="100"></th>
		                <th width="100"></th>
		            </tr>
		            </thead>
		            <tbody>
		            <tr ng-repeat="u in examinationCtrl.getAllExaminations()">
		                <td>{{u.id}}</td>
		                <td>{{u.name}}</td>
		                <td>{{examinationCtrl.getDate(u.examDate)}}</td>
		                <td>{{u.description}}</td>
		                <td>{{u.height}}</td>
		                <td>{{u.weight}}</td>
		                <td><button type="button" ng-click="examinationCtrl.editExamination(u.id)" class="btn btn-success custom-width">Edit</button></td>
		                <td><button type="button" ng-click="examinationCtrl.removeExamination(u.id)" class="btn btn-danger custom-width">Remove</button></td>
		                <td><button type="button" ng-click="examinationCtrl.getPatientBMI(u.height, u.weight)" class="btn btn-warning btn-sm" >Get BMI</button></td>
		            </tr>
		            </tbody>
		        </table>		
			</div>
			
		</div>
    </div>
</div>