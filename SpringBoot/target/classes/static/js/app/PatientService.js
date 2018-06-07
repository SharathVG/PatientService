'use strict';

angular.module('patientApp').factory('PatientService',
    ['$localStorage', '$http', '$q', 'urls', '$location',
        function ( $localStorage, $http, $q, urls, $location) {

    	 var urlParams = new URLSearchParams(window.location.search);
            var factory = {
                loadAllPatients: loadAllPatients,
                getAllPatients: getAllPatients,
                getPatient: getPatient,
                createPatient: createPatient,
                updatePatient: updatePatient,
                removePatient: removePatient,
                getAge : getAge
            };

            return factory;

            function loadAllPatients() {
                var deferred = $q.defer();
                $http.get(urls.PATIENT_SERVICE_API+'?instituteId='+urlParams.get('instituteId'))
                    .then(
                        function (response) {
                            $localStorage.Patients = response.data;
                            deferred.resolve(response);
                        },
                        function (errResponse) {
                            console.error('Error while loading Patients');
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function getAllPatients(instituteId){
            	
                return $localStorage.Patients;
            }

            function getAge(age) {
                console.log('Fetching Patient age :'+age);
                var deferred = $q.defer();
                $http.get(urls.PATIENT_SERVICE_API + "age/"+age)
                    .then(
                        function (response) {
                            console.log('Fetched successfully Patient age'+response.data);
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error while loading Patient age');
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }
            
            function getPatient(id) {
                console.log('Fetching Patient with id :'+id);
                var deferred = $q.defer();
                $http.get(urls.PATIENT_SERVICE_API + id)
                    .then(
                        function (response) {
                            console.log('Fetched successfully Patient with id :'+id);
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error while loading Patient with id :'+id);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function createPatient(Patient, instituteId) {
                console.log('Creating Patient');
                    var deferred = $q.defer();
                $http.post(urls.PATIENT_SERVICE_API+'?instituteId='+instituteId, Patient)
                    .then(
                        function (response) {
                            loadAllPatients();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                           console.error('Error while creating Patient : '+errResponse.data.errorMessage);
                           deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function updatePatient(Patient, id) {
                console.log('Updating Patient with id '+id);
                var deferred = $q.defer();
                $http.put(urls.PATIENT_SERVICE_API + id, Patient)
                    .then(
                        function (response) {
                            loadAllPatients();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error while updating Patient with id :'+id);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function removePatient(id) {
                console.log('Removing Patient with id '+id);
                var deferred = $q.defer();
                $http.delete(urls.PATIENT_SERVICE_API + id)
                    .then(
                        function (response) {
                            loadAllPatients();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error while removing Patient with id :'+id);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

        }
    ]);