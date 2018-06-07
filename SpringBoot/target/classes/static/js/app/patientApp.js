var app = angular.module('patientApp',['ui.router','ngStorage']);

app.constant('urls', {
    BASE: 'http://localhost:8081/HealthcareService',
    PATIENT_SERVICE_API : 'http://localhost:8081/HealthcareService/api/patient/'
});

app.config(['$stateProvider', '$urlRouterProvider',
    function($stateProvider, $urlRouterProvider) {
	
        $stateProvider
            .state('home', {
            	 url: '/',
            	  templateUrl: 'partials/patientList',
                controller:'PatientController',
                controllerAs:'patientCtrl',
                resolve: {
                    users: function ($q, PatientService) {
                        console.log('Load all patients');
                        var deferred = $q.defer();
                        PatientService.loadAllPatients().then(deferred.resolve, deferred.resolve);
                        return deferred.promise;
                    }
                }
            });
        $urlRouterProvider.otherwise('/');
    }]);
