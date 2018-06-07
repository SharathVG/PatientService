<!DOCTYPE html>

<html lang="en" ng-app="examinationApp">
    <head>
        <title>${title}</title>
        <link href="css/bootstrap.css" rel="stylesheet"/>
        <link href="css/app.css" rel="stylesheet"/>
    </head>
    <body>

        <div ui-view></div>
        <script src="js/lib/angular.min.js" ></script>
        <script src="js/lib/angular-ui-router.min.js" ></script>
        <script src="js/lib/localforage.min.js" ></script>
        <script src="js/lib/ngStorage.min.js"></script>
        <script src="js/app/examinationApp.js"></script>
        <script src="js/app/ExaminationService.js"></script>
        <script src="js/app/ExaminationController.js"></script>
    </body>
</html>