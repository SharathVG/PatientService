var app = angular.module('examinationApp',['ui.router','ngStorage']);

app.constant('urls', {
    BASE: 'http://localhost:8081/HealthcareService',
    EXAMINATION_SERVICE_API : 'http://localhost:8081/HealthcareService/api/examination/'
});

app.config(['$stateProvider', '$urlRouterProvider',
    function($stateProvider, $urlRouterProvider) {
	
        $stateProvider
            .state('home', {
            	 url: '/',
            	  templateUrl: 'partials/examinationList',
                controller:'ExaminationController',
                controllerAs:'examinationCtrl',
                resolve: {
                    users: function ($q, ExaminationService) {
                        console.log('Load all Examination');
                        var deferred = $q.defer();
                        ExaminationService.loadAllExaminations().then(deferred.resolve, deferred.resolve);
                        return deferred.promise;
                    }
                }
            });
        $urlRouterProvider.otherwise('/');
    }]);
