var app = angular.module('matrizriesgoController', ['smart-table']);


app.controller('matrizriesgoController',['$scope','$http','$interval','i18nService','Utilidades','$routeParams','$window','$location','$route','uiGridConstants','$mdDialog','$uibModal', '$document','$timeout','$q','$filter',
	function($scope, $http, $interval,i18nService,$utilidades,$routeParams,$window,$location,$route,uiGridConstants,$mdDialog,$uibModal,$document,$timeout,$q,$filter) {

	var mi=this;

	$window.document.title = $utilidades.sistema_nombre+' - Matriz Riesgos';
	i18nService.setCurrentLang('es');


	 $http.post('/SMatrizRiesgo', { accion: 'getMatrizRiesgos', proyectoid:'13', t: (new Date()).getTime()})
	 .then(function(response){
		 mi.lista = response.data.matrizriesgos;
		 mi.riesgos = [].concat(mi.lista);
	});

	 mi.exportarExcel = function(){
			$http.post('/SMatrizRiesgo', { accion: 'exportarExcel', proyectoid:'13',t:moment().unix(),
				  } ).then(
						  function successCallback(response) {
								var anchor = angular.element('<a/>');
							    anchor.attr({
							         href: 'data:application/ms-excel;base64,' + response.data,
							         target: '_blank',
							         download: 'Matriz_Riesgos.xls'
							     })[0].click();
							  }.bind(this), function errorCallback(response){
							 		
							 	}
						  
					);
		};
}]);



		