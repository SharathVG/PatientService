'use strict';

angular.module('patientApp').controller('PatientController',
    ['PatientService', '$scope',  '$location', '$window', function( PatientService, $scope, $location, $window) {

        var self = this;
        self.Patient = {};
        self.Patients=[];
        self.Age = 0;
        self.currentDate = new Date();
        self.ageMessage = '';
        self.submit = submit;
        self.getAllPatients = getAllPatients;
        self.createPatient = createPatient;
        self.updatePatient = updatePatient;
        self.removePatient = removePatient;
        self.editPatient = editPatient;
        self.reset = reset;
        self.goBack = goBack;
        self.getDate =getDate;
        self.getAge = getAge;
        self.addExamination = addExamination;
        self.successMessage = '';
        self.errorMessage = '';
        self.done = false;
        var urlParams = new URLSearchParams(window.location.search);
        self.instituteName = urlParams.get('instituteName');
        self.instituteId = urlParams.get('instituteId');
        self.onlyIntegers = /^\d+$/;
        self.onlyNumbers = /^\d+([,.]\d+)?$/;

        function submit() {
            console.log('Submitting'+self.instituteId + self.instituteName);
            if (self.Patient.id === undefined || self.Patient.id === null) {
                console.log('Patient being saved', self.Patient);
                createPatient(self.Patient, self.instituteId);
            } else {
                updatePatient(self.Patient, self.Patient.id);
                console.log('Patient updated with id ', self.Patient.id);
            }
        }

        function createPatient(Patient, instituteId) {
            console.log('create Patientid' + instituteId);
            PatientService.createPatient(Patient, instituteId)
                .then(
                    function (response) {
                        console.log('Patient created successfully');
                        self.successMessage = 'Patient created successfully';
                        self.errorMessage='';
                        self.done = true;
                        self.Patient={};
                        $scope.myForm.$setPristine();
                    },
                    function (errResponse) {
                        console.error('Error while creating Patient');
                        self.errorMessage = 'Error while creating Patient: ' + errResponse.data.errorMessage;
                        self.successMessage='';
                    }
                );
        }


        function updatePatient(insti, id ){
            console.log('Update Patient');
            PatientService.updatePatient(insti, id)
                .then(
                    function (response){
                        console.log('Patient updated successfully');
                        self.successMessage='Patient updated successfully';
                        self.errorMessage='';
                        self.done = true;
                        $scope.myForm.$setPristine();
                    },
                    function(errResponse){
                        console.error('Error while updating Patient');
                        self.errorMessage='Error while updating Patient '+errResponse.data;
                        self.successMessage='';
                    }
                );
        }


        function removePatient(id){
            console.log('RemovePatient with id '+id);
            PatientService.removePatient(id)
                .then(
                    function(){
                        console.log('Patient '+id + ' removed successfully');
                    },
                    function(errResponse){
                        console.error('Error while removing Patient '+id +', Error :'+errResponse.data);
                    }
                );
        }


        function getAllPatients(){
            return PatientService.getAllPatients(self.instituteId);
        }

        function editPatient(id) {
            self.successMessage='';
            self.errorMessage='';
            PatientService.getPatient(id).then(
                function (Patient) {
                	Patient.dateOfBirth = new Date(parseInt(Patient.dateOfBirth, 10))
                    self.Patient = Patient;
               	    console.log('Patient being edited', self.Patient);	
                },
                function (errResponse) {
                    console.error('Error while editing Patient ' + id + ', Error :' + errResponse.data);
                }
            );
        }
        
        function reset(){
            self.successMessage='';
            self.errorMessage='';
            self.Patient={};
            $scope.myForm.$setPristine();
        }
        
        function goBack(){    
        	$window.location.href = "/HealthcareService";
        }
        
        function getDate(dateVal){
        	dateVal = new Date(parseInt(dateVal, 10))
        	var strArray=['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'];
            var d = dateVal.getDate();
            var m = strArray[dateVal.getMonth()];
            var y = dateVal.getFullYear();
            return '' + (d <= 9 ? '0' + d : d) + '-' + m + '-' + y;
        }
        
        function getAge(dateVal){
            self.ageMessage='';
            PatientService.getAge(dateVal).then(
                function (Age) {
                 	 console.log('Age of patient',Age);	
                 	 self.ageMessage = "Patient is "+Age+" year old."
                },
                function (errResponse) {
                    console.error('Error while fetching age' + errResponse.data);
                }
            );
        }
        
        function addExamination(patientName, patientId, instituteId, instituteName){
            $window.location.href = "/HealthcareService/Examination?patientName="+patientName+"&patientId="+patientId+"&instituteId="+instituteId+"&instituteName="+instituteName;
       }
        
    }
    
    ]);