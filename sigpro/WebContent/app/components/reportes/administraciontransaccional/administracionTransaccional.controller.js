var app = angular.module('administracionTransaccionalController',['ngTouch']);
app.controller('administracionTransaccionalController',['$scope', '$http', '$interval','Utilidades','i18nService',
	function($scope, $http, $interval, $utilidades,i18nService){
		var mi = this;
		mi.paginaActual = 1;
		mi.totalPrestamo = 0;
		mi.numeroMaximoPaginas = $utilidades.numeroMaximoPaginas;
		mi.elementosPorPagina = $utilidades.elementosPorPagina;
		
		mi.mostrarcargando = true;
		$http.post('/SAdministracionTransaccional', {accion: 'getComponentes'}).success(
			function(response){
				mi.gridOptions.data = response.usuarios;
				mi.mostrarcargando = false;
			});
		
		mi.gridOptions = {
			enableRowSelection : true,
			enableRowHeaderSelection : false,
			enableColumnMenus: false,
			enablePaginationControls: true,
		    enablePaginationControls: false,
		    paginationPageSize: 50,
			useExternalFiltering: true,
			enableSorting: false,
			headerCellClass: 'text-center',
			columnDefs : [
				{name: 'usuario', displayName: 'Usuario'},
				{name: 'creados', displayName: 'Creados'},
				{name: 'actualizados', displayName: 'Actualizados'},
				{name: 'eliminados', displayName: 'Eliminados'}
			],
			onRegisterApi: function(gridApi) {
				mi.gridApi = gridApi;
			}
		}
		
		mi.gridOptions.data = [];
}]);