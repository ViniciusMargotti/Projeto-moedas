
var myApp = angular.module('myApp', []);

myApp.controller('IndexController', function IndexController($scope, $http) {

    $http.get("http://localhost:9001/api/moedas/Bitcoin").success(function (data) {
        $scope.dados = [];
        $scope.descriptionBitcoin = data[0].description;
        data.forEach(function (elemento) {
            var data = new Date(elemento.data)
            var dataFinal = data.getDay() + '/' + data.getMonth() + '/' + (parseInt(data.getYear()) - 100).toString() + ' ' + data.getHours() + ':' + data.getMinutes()
            $scope.dados.push([
                dataFinal,
                elemento.price
            ])
        });
        const schema = [{
            "name": "Time",
            "type": "date",
            "format": "%d/%m/%y %H:%M"
        }, {
            "name": "Variação preço",
            "type": "number"
        }];

        const dataStore = new FusionCharts.DataStore();
        const dataSource = {
            chart: {},
            caption: {
                text: data[0].name
            },
            subcaption: {
                text: "Variação"
            },
            yaxis: [
                {
                    plot: {
                        value: "Grocery Sales Value",
                    },
                    format: {
                        prefix: "$"
                    },
                    title: "Sale Value"
                }
            ]
        };
        dataSource.data = dataStore.createDataTable($scope.dados, schema);

        new FusionCharts({
            type: "timeseries",
            renderAt: "chart-container",
            width: "100%",
            height: "500",
            dataSource: dataSource
        }).render();
    });


    $http.get("http://localhost:9001/api/moedas/Ethereum").success(function (data) {
        $scope.dadosEthereum = [];
        debugger;
        $scope.descriptionEthereum = data[0].description;
        data.forEach(function (elemento) {
            var data = new Date(elemento.data)
            var dataFinal = data.getDay() + '/' + data.getMonth() + '/' + (parseInt(data.getYear()) - 100).toString() + ' ' + data.getHours() + ':' + data.getMinutes()
            $scope.dadosEthereum.push([
                dataFinal,
                elemento.price
            ])
        });
        const schema = [{
            "name": "Time",
            "type": "date",
            "format": "%d/%m/%y %H:%M"
        }, {
            "name": "Variação preço",
            "type": "number"
        }];

        const dataStore = new FusionCharts.DataStore();
        const dataSource = {
            chart: {},
            caption: {
                text: data[0].name
            },
            subcaption: {
                text: "Variação"
            },
            yaxis: [
                {
                    plot: {
                        value: "Grocery Sales Value",
                    },
                    format: {
                        prefix: "$"
                    },
                    title: "Sale Value"
                }
            ]
        };
        dataSource.data = dataStore.createDataTable($scope.dadosEthereum, schema);

        new FusionCharts({
            type: "timeseries",
            renderAt: "chart-container-2",
            width: "100%",
            height: "500",
            dataSource: dataSource
        }).render();
    });



});