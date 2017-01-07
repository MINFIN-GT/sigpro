var app = angular.module('usuarioController', [ 'ngTouch', 'ui.grid.edit' ]);

app.controller(
 'usuarioController',
 [
  '$scope',
  '$http',
  '$interval',
  '$q',
  'i18nService',
  'Utilidades',
  '$routeParams',
  'uiGridConstants',
  '$mdDialog',
  '$window',
  '$location',
  '$route',
  function($scope, $http, $interval, $q,i18nService,$utilidades,$routeParams,uiGridConstants,$mdDialog, $window, $location, $route) {
	var mi=this;
	$window.document.title = 'SIGPRO - Usuarios';
	i18nService.setCurrentLang('es');
	mi.mostrarcargando=true;
	mi.entityselected = null;
	mi.esNuevo = false;
	mi.paginaActual = 1;
	mi.numeroMaximoPaginas = $utilidades.numeroMaximoPaginas;
	mi.elementosPorPagina = $utilidades.elementosPorPagina;
	mi.permisoSelected={id:"",nombre:"", descripcion:""};	
	mi.usuariosSelected={usuario:"", email:"",password:"", usuarioCreo:"", fechaCreacion:"", usuarioActualizo:"", fechaActualizacion:""};
	mi.claves={password1:"", password2:""};
	mi.nuevosPermisos=[];
	mi.permisosEliminados=[];
	mi.gridOptions = {
		enableRowSelection : true,
		enableRowHeaderSelection : false,
		paginationPageSizes : [ 25, 50, 75 ],
		paginationPageSize : 25,
		data : [],
		columnDefs : [ {
			name : 'Usuario',
			field : 'usuario'
		}, {
			name : 'Correo',
			field : 'email'
		}, {
			name : 'Fecha Creación',
			field : 'fechaCreacion'
		}, {
			name: 'Usuario creó',
			field: 'usuarioCreo'
		},{
			name: 'Usuario Actualizó',
			field: 'usuarioActualizo'
		}
		],

	};
	mi.cargarTabla=function(pagina){
		$http.post('/SUsuario',
				{ accion : 'getUsuarios',  pagina: pagina, numeroUsuarios: $utilidades.elementosPorPagina  }).success(function(data) {
				mi.gridOptions.data =  data.usuarios;
				mi.mostrarcargando=false;
				mi.isCollapsed = false;
		});
	};
	
	mi.gridOptions.multiSelect = false;
	mi.gridOptions.modifierKeysToMultiSelect = false;
	mi.gridOptions.noUnselect = true;
	mi.gridOptions.onRegisterApi = function(gridApi) {
		mi.gridApi = gridApi;
		gridApi.selection.on
		.rowSelectionChanged(
			$scope,
			function(row) {
			var msg = 'row selected '
			+ row;
			mi.usuariosSelected = row.entity;
		});
		if($routeParams.reiniciar_vista=='rv'){
			mi.guardarEstado();
	    }
	    else{
	    	  $http.post('/SEstadoTabla', { action: 'getEstado', grid:'usuarios', t: (new Date()).getTime()}).then(function(response){
		      if(response.data.success && response.data.estado!='')
		    	  mi.gridApi.saveState.restore( $scope, response.data.estado);
		    	  mi.gridApi.colMovable.on.columnPositionChanged($scope, mi.guardarEstado);
			      mi.gridApi.colResizable.on.columnSizeChanged($scope, mi.guardarEstado);
			      mi.gridApi.core.on.columnVisibilityChanged($scope, mi.guardarEstado);
			      mi.gridApi.core.on.sortChanged($scope, mi.guardarEstado);
			  });
	    }
	};

   
						
						
	

	

	mi.cancelar = function() {
		mi.isCollapsed = false;
	}
	
	/*mi.nuevoPermiso=function(){
		var formularios = mi.cargarTabla(mi.paginaActual);						
		mi.isCollapsed = true;
		mi.entityselected = null;
		mi.esNuevo = true;
		mi.permisoSelected = {id:"",nombre:"", descripcion:""};
	};	*/			
	
	mi.nuevoUsuario=function(){
		mi.usuariosSelected={usuario:"", email:"",password:"", usuarioCreo:"", fechaCreacion:"", usuarioActualizo:"", fechaActualizacion:""};
		mi.isCollapsed = true;
		mi.entityselected = null;
		mi.esNuevo = true;
	};
	
	mi.guardarUsuario=function(){
		if(mi.esNuevo){
			if(mi.claves.password1!=="" && mi.claves.password2!=="" && mi.usuarioSelected.usuario!=="" && mi.usuarioSelected.email!==""){
				if(validarEmail(mi.usuarioSelected.email)){
					if(mi.claves.password1===mi.claves.password2){
						mi.usuarioSelected.password= mi.claves.password1;
						$http.post('/SUsuario',
								{
									accion: 'registroUsuario',
									usuario:mi.usuarioSelected.usuario,
									email:mi.usuarioSelected.email,
									password:mi.usuarioSelected.password,
									permisos:JSON.stringify(mi.nuevosPermisos)
								}).success(
									function(data) {
										if(data.success){
											mi.cargarTabla(mi.paginaActual);
											mi.usuariosSelected={usuario:"", email:"",password:"", usuarioCreo:"", fechaCreacion:"", usuarioActualizo:"", fechaActualizacion:""};
										}
							});
					}else{
						$utilidades.mensaje('danger','No coinciden la contraseña y su confirmación.');
					}
				}else{
					$utilidades.mensaje('danger','correo electrónico no válido.');
				}
				
			}else{
				$utilidades.mensaje('danger','Los campos no deben de quedar vacios.');
			}
		}else{
			
		}
	};
	
	
	
	mi.eliminarUsuario=function(ev){
		if(mi.usuariosSelected.usuario!==""){
			var confirm = $mdDialog.confirm()
	          .title('Confirmación de borrado')
	          .textContent('¿Desea borrar al usuario "'+mi.usuariosSelected.usuario+'"?')
	          .ariaLabel('Confirmación de borrado')
	          .targetEvent(ev)
	          .ok('Borrar')
	          .cancel('Cancelar');
			$mdDialog.show(confirm).then(function() {
		    	$http.post('/SUsuario', {
					accion: 'eliminarUsuario',
					usuario: mi.usuariosSelected.usuario
				}).success(function(response){
					if(response.success){
						$utilidades.mensaje('success','Usuario elimiado con éxito');
						mi.cargarTabla(mi.paginaActual);
						mi.usuariosSelected={usuario:"", email:"",password:"", usuarioCreo:"", fechaCreacion:"", usuarioActualizo:"", fechaActualizacion:""};
					}
					else
						$utilidades.mensaje('danger','Error al eliminar el usuario');
				});
		    }, function() {
		    
		    });
		}else{
		    $utilidades.mensaje('danger','Seleccione un usuario');
		}
	};
	function validarEmail(email) {
	    var re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
	    return re.test(email);
	}
	
	/*mi.editarPermiso=function(){
		if(mi.permisoSelected.id!==""){
			mi.isCollapsed = true;
			mi.esNuevo=false;
		}else{
			$utilidades.mensaje('danger','Seleccione un permiso');
		}
	};*/
	
	/*mi.reiniciarVista=function(){
		if($location.path()=='/permisos/rv')
			$route.reload();
		else
			$location.path('/permisos/rv');
	};*/
	
	mi.cambiarPagina=function(){
		mi.cargarTabla(mi.paginaActual);
	};
	
	mi.guardarEstado=function(){
		var estado = mi.gridApi.saveState.save();
		var tabla_data = { action: 'guardaEstado', grid:'usuarios', estado: JSON.stringify(estado), t: (new Date()).getTime() }; 
		$http.post('/SEstadoTabla', tabla_data).then(function(response){
			
		});
	}
	
	$http.post('/SUsuario', { accion: 'getTotalUsuarios' }).success(
			function(response) {
				mi.totalUsuarios = response.totalUsuarios;
				mi.cargarTabla(mi.paginaActual);
	});
} ]);