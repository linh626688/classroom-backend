clvar
app = angular.module('classRoomManager', []);

app.controller("ClassRoomCtrl", function ($scope, $http) {

    $scope.classRooms = [];

    $scope.loadClassRooms = function () {
        var httpRequest = $http({
            method: 'GET',
            url: 'http://localhost:8080/classes'

        }).success(function (data, status) {
            $scope.classRooms = data;
        });
    };
});