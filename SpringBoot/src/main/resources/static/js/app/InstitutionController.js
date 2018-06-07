'use strict';

angular.module('crudApp').controller('InstitutionController',
    ['InstitutionService', '$scope', '$window', '$location', '$localStorage', '$sessionStorage', function( InstitutionService, $scope, $window, $location, $localStorage, $sessionStorage) {

        var self = this;
        self.Institution = {};
        self.Institutions=[];

        self.submit = submit;
        self.getAllInstitutions = getAllInstitutions;
        self.createInstitution = createInstitution;
        self.updateInstitution = updateInstitution;
        self.removeInstitution = removeInstitution;
        self.editInstitution = editInstitution;
        self.reset = reset;
        self.addPatient = addPatient;

        self.successMessage = '';
        self.errorMessage = '';
        self.done = false;

        self.onlyIntegers = /^\d+$/;
        self.onlyNumbers = /^\d+([,.]\d+)?$/;

        function submit() {
            console.log('Submitting');
            if (self.Institution.id === undefined || self.Institution.id === null) {
                console.log('Institution being saved', self.Institution);
                createInstitution(self.Institution);
            } else {
                updateInstitution(self.Institution, self.Institution.id);
                console.log('Institution updated with id ', self.Institution.id);
            }
        }

        function createInstitution(Institution) {
            console.log('create institution');
            InstitutionService.createInstitution(Institution)
                .then(
                    function (response) {
                        console.log('Institution created successfully');
                        self.successMessage = 'Institution created successfully';
                        self.errorMessage='';
                        self.done = true;
                        self.Institution={};
                        $scope.myForm.$setPristine();
                    },
                    function (errResponse) {
                        console.error('Error while creating Institution');
                        self.errorMessage = 'Error while creating Institution: ' + errResponse.data.errorMessage;
                        self.successMessage='';
                    }
                );
        }


        function updateInstitution(insti, id){
            console.log('Update institution');
            InstitutionService.updateInstitution(insti, id)
                .then(
                    function (response){
                        console.log('Institution updated successfully');
                        self.successMessage='Institution updated successfully';
                        self.errorMessage='';
                        self.done = true;
                        $scope.myForm.$setPristine();
                    },
                    function(errResponse){
                        console.error('Error while updating Institution');
                        self.errorMessage='Error while updating Institution '+errResponse.data;
                        self.successMessage='';
                    }
                );
        }


        function removeInstitution(id){
            console.log('RemoveInstitution with id '+id);
            InstitutionService.removeInstitution(id)
                .then(
                    function(){
                        console.log('Institution '+id + ' removed successfully');
                    },
                    function(errResponse){
                        console.error('Error while removing Institution '+id +', Error :'+errResponse.data);
                    }
                );
        }


        function getAllInstitutions(){
            return InstitutionService.getAllInstitutions();
        }

        function editInstitution(id) {
            self.successMessage='';
            self.errorMessage='';
            InstitutionService.getInstitution(id).then(
                function (Institution) {
                    self.Institution = Institution;
                },
                function (errResponse) {
                    console.error('Error while editing Institution ' + id + ', Error :' + errResponse.data);
                }
            );
        }
        
        function reset(){
            self.successMessage='';
            self.errorMessage='';
            self.Institution={};
            $scope.myForm.$setPristine();
        }
        
        function addPatient(name, id){
             $window.location.href = "/HealthcareService/Patient?instituteName="+name+"&instituteId="+id;
        }
       
    }
    
    ]);