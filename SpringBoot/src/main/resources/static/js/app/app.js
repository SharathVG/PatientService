var app = angular.module('crudApp',['ui.router','ngStorage']);

app.constant('urls', {
    BASE: 'http://localhost:8081/HealthcareService',
    INSTITUTION_SERVICE_API : 'http://localhost:8081/HealthcareService/api/institution/'
});

app.config(['$stateProvider', '$urlRouterProvider',
    function($stateProvider, $urlRouterProvider) {

	 $stateProvider
     .state('home', {
         url: '/',
         templateUrl: 'partials/list',
         controller:'InstitutionController',
         controllerAs:'ctrl',
         resolve: {
             users: function ($q, InstitutionService) {
                 console.log('Load all Institution');
                 var deferred = $q.defer();
                 InstitutionService.loadAllInstitutions().then(deferred.resolve, deferred.resolve);
                 return deferred.promise;
             }
         }
     })
        
        $urlRouterProvider.otherwise('/');
    }]);

