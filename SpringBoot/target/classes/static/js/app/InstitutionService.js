'use strict';

angular.module('crudApp').factory('InstitutionService',
    ['$localStorage', '$http', '$q', 'urls',
        function ($localStorage, $http, $q, urls) {

            var factory = {
                loadAllInstitutions: loadAllInstitutions,
                getAllInstitutions: getAllInstitutions,
                getInstitution: getInstitution,
                createInstitution: createInstitution,
                updateInstitution: updateInstitution,
                removeInstitution: removeInstitution
            };

            return factory;

            function loadAllInstitutions() {
                console.log('Fetching all institutions');
                var deferred = $q.defer();
                $http.get(urls.INSTITUTION_SERVICE_API)
                    .then(
                        function (response) {
                            console.log('Fetched successfully all institutions');
                            $localStorage.institutions = response.data;
                            deferred.resolve(response);
                        },
                        function (errResponse) {
                            console.error('Error while loading institutions');
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function getAllInstitutions(){
                return $localStorage.institutions;
            }

            function getInstitution(id) {
                console.log('Fetching Institution with id :'+id);
                var deferred = $q.defer();
                $http.get(urls.INSTITUTION_SERVICE_API + id)
                    .then(
                        function (response) {
                            console.log('Fetched successfully Institution with id :'+id);
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error while loading Institution with id :'+id);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function createInstitution(Institution) {
                console.log('Creating Institution');
                var deferred = $q.defer();
                $http.post(urls.INSTITUTION_SERVICE_API, Institution)
                    .then(
                        function (response) {
                            loadAllInstitutions();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                           console.error('Error while creating Institution : '+errResponse.data.errorMessage);
                           deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function updateInstitution(Institution, id) {
                console.log('Updating Institution with id '+id);
                var deferred = $q.defer();
                $http.put(urls.INSTITUTION_SERVICE_API + id, Institution)
                    .then(
                        function (response) {
                            loadAllInstitutions();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error while updating Institution with id :'+id);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function removeInstitution(id) {
                console.log('Removing Institution with id '+id);
                var deferred = $q.defer();
                $http.delete(urls.INSTITUTION_SERVICE_API + id)
                    .then(
                        function (response) {
                            loadAllInstitutions();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error while removing Institution with id :'+id);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

        }
    ]);