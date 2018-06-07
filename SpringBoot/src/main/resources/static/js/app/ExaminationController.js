'use strict';

angular.module('examinationApp').controller('ExaminationController',
    ['ExaminationService', '$scope',  '$location', '$window', function( ExaminationService, $scope, $location, $window) {

        var self = this;
        self.Examination = {};
        self.Examinations=[];
        self.Age = 0;
        self.currentDate = new Date();
        self.ageMessage = '';
        self.submit = submit;
        self.getAllExaminations = getAllExaminations;
        self.createExamination = createExamination;
        self.updateExamination = updateExamination;
        self.removeExamination = removeExamination;
        self.editExamination = editExamination;
        self.reset = reset;
        self.goBack = goBack;
        self.getDate =getDate;
        self.getPatientBMI= getPatientBMI;
        self.successMessage = '';
        self.errorMessage = '';
        self.done = false;
        var urlParams = new URLSearchParams(window.location.search);
        self.patientName = urlParams.get('patientName');
        self.patientId = urlParams.get('patientId');
        self.institueName = urlParams.get('institueName');
        self.institueId = urlParams.get('institueId');

        function submit() {
            console.log('Submitting'+self.examinationId + self.examinationName);
            if (self.Examination.id === undefined || self.Examination.id === null) {
                console.log('exam being saved', self.Examination);
                createExamination(self.Examination, self.patientId);
            } else {
                updateExamination(self.Examination, self.Examination.id);
                console.log('exam updated with id ', self.Examination.id);
            }
        }

        function createExamination(Examination, patientId) {
            console.log('create exam for patient with id {}' + patientId);
            ExaminationService.createExamination(Examination, patientId)
                .then(
                    function (response) {
                        console.log('Exam created successfully');
                        self.successMessage = 'Exam created successfully';
                        self.errorMessage='';
                        self.done = true;
                        self.Examination={};
                        $scope.myForm.$setPristine();
                    },
                    function (errResponse) {
                        console.error('Error while creating exam');
                        self.errorMessage = 'Error while creating exam: ' + errResponse.data.errorMessage;
                        self.successMessage='';
                    }
                );
        }


        function updateExamination(exam, id ){
            console.log('Update exam');
            ExaminationService.updateExamination(exam, id)
                .then(
                    function (response){
                        console.log('Examination updated successfully');
                        self.successMessage='Examination updated successfully';
                        self.errorMessage='';
                        self.done = true;
                        $scope.myForm.$setPristine();
                    },
                    function(errResponse){
                        console.error('Error while updating Examination');
                        self.errorMessage='Error while updating Examination '+errResponse.data;
                        self.successMessage='';
                    }
                );
        }


        function removeExamination(id){
            console.log('RemoveExamination with id '+id);
            ExaminationService.removeExamination(id)
                .then(
                    function(){
                        console.log('Examination '+id + ' removed successfully');
                    },
                    function(errResponse){
                        console.error('Error while removing Examination '+id +', Error :'+errResponse.data);
                    }
                );
        }


        function getAllExaminations(){
            return ExaminationService.getAllExaminations(self.patientId);
        }

        function editExamination(id) {
            self.successMessage='';
            self.errorMessage='';
            ExaminationService.getExamination(id).then(
                function (Examination) {
                	Examination.examDate = new Date(parseInt(Examination.examDate, 10))
                    self.Examination = Examination;
               	    console.log('Examination being edited', self.Examination);	
                },
                function (errResponse) {
                    console.error('Error while editing Examination ' + id + ', Error :' + errResponse.data);
                }
            );
        }
        
        function reset(){
            self.successMessage='';
            self.errorMessage='';
            self.Examination={};
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
        
        function bmiConclusion(bmi){
            if(bmi <= 18.5)
                return 'underweight';
            else if(bmi > 18.5 && bmi <= 24.9 )
                return 'within the average range';
            else if(bmi > 24.9  && bmi <= 30)
                return 'overweight';
            else if(bmi > 30)
                return ' obese';
            else
                return '';
        }
        
        function getPatientBMI(height, weight){
            self.ageMessage='';
            ExaminationService.getPatientBMI(height, weight).then(
                function (BMI) {
                 	 console.log('BMI of patient',BMI);	
                 	 self.ageMessage = "BMI of patient is "+BMI + " and patient is " + bmiConclusion(BMI); 
                },
                function (errResponse) {
                    console.error('Error while fetching BMI' + errResponse.data);
                }
            );
        }
        
    }
    
    ]);