'use strict';

angular.module('examinationApp').factory('ExaminationService',
    ['$localStorage', '$http', '$q', 'urls', '$location',
        function ( $localStorage, $http, $q, urls, $location) {

    	 var urlParams = new URLSearchParams(window.location.search);
            var factory = {
                loadAllExaminations: loadAllExaminations,
                getAllExaminations: getAllExaminations,
                getExamination: getExamination,
                createExamination: createExamination,
                updateExamination: updateExamination,
                removeExamination: removeExamination,
                getPatientBMI : getPatientBMI
            };

            return factory;

            function loadAllExaminations() {
                var deferred = $q.defer();
                console.log(urls.EXAMINATION_SERVICE_API+'?patientId='+urlParams.get('patientId'))
                $http.get(urls.EXAMINATION_SERVICE_API+'?patientId='+urlParams.get('patientId'))
                    .then(
                        function (response) {
                            $localStorage.Examinations = response.data;
                            deferred.resolve(response);
                        },
                        function (errResponse) {
                            console.error('Error while loading Examinations');
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function getAllExaminations(patientId){
            	
                return $localStorage.Examinations;
            }

            function getPatientBMI(height, weight) {
                console.log('Fetching Patient BMI :');
                var deferred = $q.defer();
                $http.get(urls.EXAMINATION_SERVICE_API + "bmi/height/"+height+"/weight/"+weight)
                    .then(
                        function (response) {
                            console.log('Fetched successfully Patient bmi'+response.data);
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error while loading Patient bmi');
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }
            
            function getExamination(id) {
                console.log('Fetching Examination with id :'+id);
                var deferred = $q.defer();
                $http.get(urls.EXAMINATION_SERVICE_API + id)
                    .then(
                        function (response) {
                            console.log('Fetched successfully Examination with id :'+id);
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error while loading Examination with id :'+id);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function createExamination(Examination, patientId) {
                console.log('Creating Examination');
                    var deferred = $q.defer();
                $http.post(urls.EXAMINATION_SERVICE_API+'?patientId='+patientId, Examination)
                    .then(
                        function (response) {
                            loadAllExaminations();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                           console.error('Error while creating Examination : '+errResponse.data.errorMessage);
                           deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function updateExamination(Examination, id) {
                console.log('Updating Examination with id '+id);
                var deferred = $q.defer();
                $http.put(urls.EXAMINATION_SERVICE_API + id, Examination)
                    .then(
                        function (response) {
                            loadAllExaminations();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error while updating Examination with id :'+id);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function removeExamination(id) {
                console.log('Removing Examination with id '+id);
                var deferred = $q.defer();
                $http.delete(urls.EXAMINATION_SERVICE_API + id)
                    .then(
                        function (response) {
                            loadAllExaminations();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error while removing Examination with id :'+id);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

        }
    ]);