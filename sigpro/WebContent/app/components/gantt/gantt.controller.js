var app = angular.module('ganttController', ['DlhSoft.ProjectData.GanttChart.Directives','DlhSoft.Kanban.Angular.Components']);

var GanttChartView = DlhSoft.Controls.GanttChartView;
//Query string syntax: ?theme
//Supported themes: Default, Generic-bright, Generic-blue, DlhSoft-gray, Purple-green, Steel-blue, Dark-black, Cyan-green, Blue-navy, Orange-brown, Teal-green, Purple-beige, Gray-blue, Aero.
var queryString = window.location.search;
var theme = queryString ? queryString.substr(1) : null;

app.controller('ganttController',['$scope','$http','$interval','i18nService','Utilidades','$routeParams','$window','$location','$route','uiGridConstants','$mdDialog','$uibModal', '$document','$timeout','$q',
	function($scope, $http, $interval,i18nService,$utilidades,$routeParams,$window,$location,$route,uiGridConstants,$mdDialog,$uibModal,$document,$timeout,$q) {

		var mi=this;
		mi.proyectoid = "";
		mi.proyectoNombre = "";
		mi.objetoTipoNombre = "";
		var date = new Date(), year = date.getFullYear(), month = date.getMonth();
		

		$window.document.title = $utilidades.sistema_nombre+' - Gantt';
		
		$http.post('/SProyecto', { accion: 'obtenerProyectoPorId', id: $routeParams.proyectoId }).success(
				function(response) {
					mi.proyectoid = response.id;
					mi.proyectoNombre = response.nombre;
					mi.objetoTipoNombre = "Proyecto";
		});
		
		mi.zoom = 2.5;
		var date = new Date(), year = date.getFullYear(), month = date.getMonth();
		var items=[];
		
		mi.nombreArchivo="";
		
		mi.getStatus = function (item) {
		    if (item.hasChildren || item.isMilestone)
		        return '';
		    if (item.completedFinish >= item.finish)
		        return 'Completada';
		    var now = settings.currentTime;
		    if (item.completedFinish < now)
		        return 'Atrasada';
		    if (item.completedFinish > item.start)
		        return 'En progreso';
		    return 'To do';
		}
		
		mi.getStatusColor = function (status) {
		    switch (status) {
		        case 'Completada':
		            return 'Green';
		        case 'To do':
		            return 'Gray';
		        case 'Atrasada':
		            return 'Red';
		        case 'En progreso':
		            return 'Orange';
		        default:
		            return 'Transparent';
		    }
		}
		var settings = { 
				areTaskDependencyConstraintsEnabled: true,
				currentTime: new Date(),
				itemHeight: 30,
				barCornerRadius: 8,
				months: ['Enero','Febrero','Marzo','Abril','Mayo','Junio','Julio','Agosto','Septiembre','Octubre','Noviembre','Diciembre'],
				daysOfWeek: ['D','L','M','M','J','V','S'],
				dateFormatter: function (date){  return moment(date).format("DD/MM/YYYY");  },
				dateTimeFormatter: function (dateTime) { return moment(dateTime).format("DD/MM/YYYY");  },
				isMouseWheelZoomEnabled: false,
				horizontalGridLines: '#e0e0e0',
				itemTemplate: function (item) {
				    var document = item.ganttChartView.ownerDocument;
				    var toolTip = document.createElementNS('http://www.w3.org/2000/svg', 'title');
				    var toolTipContent = item.content +'\n • ' + (!item.isMilestone ? 'Inicio: ' : 'Fecha: ')  + moment(item.start).format("DD/MM/YYYY");
				    if (!item.isMilestone)
				        toolTipContent += '\n • ' + 'Fin: ' + moment(item.finish).format("DD/MM/YYYY");
				    toolTipContent += (item.parent!=null) ? '\n • ' + 'Padre: '+ item.parent.content : '';
				    toolTip.appendChild(document.createTextNode(toolTipContent));
				    return toolTip;
				},
				scales:[{ scaleType: 'NonworkingTime', isHeaderVisible: false, isHighlightingVisible: true, highlightingStyle: 'stroke-width: 0; fill: #f8f8f8' },
			    		{ scaleType: 'Months', headerTextFormat: 'Month', headerStyle: 'padding: 7px 5px; border-right: solid 1px White; border-bottom: solid 1px White; color: gray', isSeparatorVisible: true, separatorStyle: 'stroke: #c8bfe7; stroke-width: 1px' },
			    		{ scaleType: 'Days', headerTextFormat: 'Day', headerStyle: 'padding: 7px 5px; border-right: solid 1px White; border-bottom: solid 1px White; color: gray', isSeparatorVisible: false, separatorStyle: 'stroke: #c8bfe7; stroke-width: 0.25px' }]
			    
		};
		
		
		// Default Columns
		var columns = DlhSoft.Controls.GanttChartView.getDefaultColumns(items, settings);
		
		columns.splice(0, 0, {
		    header: 'Orden', 
		    width: 50,
		    isReadOnly: true,
		    cellStyle: 'text-align: right;',
		    cellTemplate: function (item) {
		    	//return DlhSoft.Controls.GanttChartView.numberInputColumnTemplateBase(document, 50, function(){ return item.index+1 }, function(value){ item.index=value+1 })
		    	return DlhSoft.Controls.GanttChartView.textColumnTemplateBase(document,  function(){ return item.index+1 })
		        //return DlhSoft.Controls.GanttChartView.textColumnTemplateBase(document, function () { return mi.getStatus(item); });
		    }
		});
		
		columns.splice(2, 0, {
		    header: 'Estado', width: 120,
		    cellTemplate: function (item) {
		        return DlhSoft.Controls.GanttChartView.textColumnTemplateBase(document, function () { return mi.getStatus(item); });
		    }
		});
		
		columns.splice(2, 0, {
		    header: '', width: 30,
		    cellTemplate: function (item) {
		        var rectangle = document.createElement('div');
		        rectangle.innerHTML = '&nbsp;';
		        rectangle.setAttribute('style', 'background-color: ' + mi.getStatusColor(mi.getStatus(item)));
		        return rectangle;
		    }
		});
		
		columns.splice(9,1);
		
		columns[1].header = 'Nombre';
		columns[1].width = 300;
		columns[4].header = 'Inicio';
		columns[5].header = 'Fin';
		columns[6].header = 'Hito';
		columns[6].isReadOnly = true;
		columns[7].header = 'Completada';
		columns[8].header = 'Responsable';
		columns[8].isReadOnly = true;
		
		
		
		for(var i=0; i<columns.length;i++)
			columns[i].headerClass = 'gantt-chart-header-column';
		
		settings.columns = columns;
		
		
		
		settings.itemPropertyChangeHandler = function (item, propertyName, isDirect, isFinal) {
			
		    if (isDirect && isFinal && false){
		    	
		    	if(propertyName=='start' || propertyName=='finish' || propertyName=='content' 
		    		|| propertyName=='completedFinish' ) {
		    		
		    		console.log(item.content + '.' + propertyName + ' changed.');
		    		console.log (item);
		    		
		    		var parametros = {
		    				accion: 'modificarData',
		    				objetoId: item.objetoId,
		    				objetoTipo:item.objetoTipo,
		    				nombre: item.content,
		    				inicio: moment(item.start).format('DD/MM/YYYY'),
		    				fin: moment(item.finish).format('DD/MM/YYYY'),
		    				completada: item.completedFinish != '' ? true : false
					}
		    	
					$http.post('/SGantt',parametros).success(
						function(response) {
							if (response.success){
								$utilidades.mensaje('success','Item modificado con éxito');
							}else{
								$utilidades.mensaje('danger','Error al guardar item');
							}
					});
		    	}
		    }
		}
		
		$scope.settings = settings;
		
		mi.ganttChartView;
		var formatData = new FormData();
		 
		formatData.append("accion",'getProyecto');
		formatData.append("proyecto_id",$routeParams.proyectoId);
		
		$http.post('/SGantt', formatData, {
			headers: {'Content-Type': undefined},
			transformRequest: angular.identity
		 }).success(
				function(response) {
					var items = response.items;
					$scope.settings.displayedTime = moment(items[0].start,'DD/MM/YYYY hh:mm:ss').toDate();
					
					for(var i=0; i< items.length; i++){
						if(items[i].start)
							items[i].start = moment(items[i].start,'DD/MM/YYYY hh:mm:ss').toDate();
						if(items[i].finish)
							items[i].finish = moment(items[i].finish,'DD/MM/YYYY hh:mm:ss').toDate();
						if(items[i].identation)
							items[i].indentation = Number(items[i].indentation);
						items[i].expandend = items[i].expanded=='true' ? true : false;
						items[i].isMilestone = items[i].isMilestone=='true' ? true : false;
					}
					$scope.items = items;
					$scope.settings.timelineStart =items[0].start;
					mi.ganttChartView = document.getElementById('ganttChartView');
					
					
					var predecesores = response.predecesores;
					
					for (var predecesor in predecesores){
						for(var i=0; i< items.length; i++){
							if (items[i].id === predecesores[predecesor].id){
								for(var j=0; j< items.length; j++){
									if (items[j].id == predecesores[predecesor].idPredecesor){
										items[i].predecessors = [{ item: items[j] }];
										break;
									}
								}
								break;
							}
						}
					}
		});	
		
		mi.zoomAcercar = function(){
			mi.zoom =(mi.zoom<1) ? mi.zoom + 0.05 :  mi.zoom + 1;
			mi.ganttChartView.setHourWidth(mi.zoom);
		};
		
		mi.zoomAlejar = function(){
			mi.zoom = (mi.zoom<1) ? mi.zoom - 0.05 :  mi.zoom - 1;
			if(mi.zoom<0.05){
				mi.zoom=0.05;
				$utilidades.mensaje('warning','No puede alejar mas la vista de la gráfica');
			}
			else
				mi.ganttChartView.setHourWidth(mi.zoom);
		};
		
		mi.cargar=function(){
			if (mi.archivos!=null && mi.arhivos != ''){
			
			var formatData = new FormData();
			formatData.append("file",mi.archivos);  
			formatData.append("accion",'importar');
			$http.post('/SGantt',formatData, {
					headers: {'Content-Type': undefined},
					transformRequest: angular.identity
				 } ).then(
			
				function(response) {
					var items = response.data.items;
					$scope.settings.displayedTime = moment(items[0].start,'DD/MM/YYYY hh:mm:ss').toDate();
					
					for(var i=0; i< items.length; i++){
						if(items[i].start)
							items[i].start = moment(items[i].start,'DD/MM/YYYY hh:mm:ss').toDate();
						if(items[i].finish)
							items[i].finish = moment(items[i].finish,'DD/MM/YYYY hh:mm:ss').toDate();
						if(items[i].identation)
							items[i].indentation = Number(items[i].indentation);
						items[i].expandend = items[i].expanded=='true' ? true : false;
						items[i].isMilestone = items[i].isMilestone=='true' ? true : false;
					}
					$scope.items = items;
					$scope.settings.timelineStart =items[0].start;
					mi.ganttChartView = document.getElementById('ganttChartView');
				}
			);
			}else{
				$utilidades.mensaje('danger','Debe seleccionar un archivo');
			}
		};
		
		mi.exportar=function(){
			var formatData = new FormData();
			
			$http.post('/SDownload', { accion: 'exportar', proyectoid:$routeParams.proyectoId,t:moment().unix()
			  }).then(
					 function successCallback(response) {
							var anchor = angular.element('<a/>');
						    anchor.attr({
						         href: 'data:application/ms-project;base64,' + response.data,
						         target: '_blank',
						         download: 'Programa.mpx'
						     })[0].click();
						  }.bind(this), function errorCallback(response){
						 		
						 	}
			);
		};
		
		
		 $scope.cargarArchivo = function(event){
		         mi.archivos = event.target.files[0];      
		         mi.nombreArchivo = mi.archivos.name;
		       
		  };
		  
		  settings.itemDoubleClickHandler = function (isOnChart, item){
			console.log (isOnChart);
			console.log (item);
			switch (item.objetoTipo){
				case '1':
					mi.editarPrestamo(item.objetoId,item.index);
					break;
				case '2':
					mi.editarComponente(item.objetoId,item.index);
					break;
				case '3':
					mi.editarProducto(item.objetoId,item.index);
					break;
				case '4':
					mi.editarSubproducto(item.objetoId,item.index);
					break;
				case '5':
					mi.editarActividad(item.objetoId,item.index);
					break;
			}
		  }
		  
		  mi.editarPrestamo = function(idprestamo,index) {
				var modalInstance = $uibModal.open({
					animation : 'true',
					ariaLabelledBy : 'modal-title',
					ariaDescribedBy : 'modal-body',
					templateUrl : 'editarPrestamo.jsp',
					controller : 'modalEditarPrestamo',
					controllerAs : 'prestamoc',
					backdrop : 'static',
					size : 'md',
					resolve : {
						idprestamo: function() {
							return idprestamo;
						},
						index: function(){
							return index;
						}
					}
				});

				modalInstance.result.then(function(resultado) {
					if (resultado != undefined){
						$scope.items[index].content = resultado.nombre;
						$utilidades.mensaje('success','Item modificado con éxito');
					}else{
						$utilidades.mensaje('danger', 'Error al guardar el item de prestamo');
					}

				}, function() {
				});
		};
		
		
		mi.editarComponente = function(idcomponente,index) {
			var modalInstance = $uibModal.open({
				animation : 'true',
				ariaLabelledBy : 'modal-title',
				ariaDescribedBy : 'modal-body',
				templateUrl : 'editarComponente.jsp',
				controller : 'modalEditarComponente',
				controllerAs : 'componentec',
				backdrop : 'static',
				size : 'md',
				resolve : {
					idcomponente: function() {
						return idcomponente;
					},
					index: function(){
						return index;
					}
				}
			});

			modalInstance.result.then(function(resultado) {
				if (resultado != undefined){
					$scope.items[index].content = resultado.nombre;
					$utilidades.mensaje('success','Item modificado con éxito');
				}else{
					$utilidades.mensaje('danger', 'Error al guardar el item de componente');
				}

			}, function() {
			});
		};
		
		mi.editarProducto = function(idproducto,index) {
			var modalInstance = $uibModal.open({
				animation : 'true',
				ariaLabelledBy : 'modal-title',
				ariaDescribedBy : 'modal-body',
				templateUrl : 'editarProducto.jsp',
				controller : 'modalEditarProducto',
				controllerAs : 'productoc',
				backdrop : 'static',
				size : 'md',
				resolve : {
					idproducto: function() {
						return idproducto;
					},
					index: function(){
						return index;
					}
				}
			});

			modalInstance.result.then(function(resultado) {
				if (resultado != undefined){
					$scope.items[index].content = resultado.nombre;
					$utilidades.mensaje('success','Item modificado con éxito');
				}else{
					$utilidades.mensaje('danger', 'Error al guardar el item de producto');
				}

			}, function() {
			});
		};
		
		mi.editarSubproducto = function(idsubproducto,index) {
			var modalInstance = $uibModal.open({
				animation : 'true',
				ariaLabelledBy : 'modal-title',
				ariaDescribedBy : 'modal-body',
				templateUrl : 'editarSubproducto.jsp',
				controller : 'modalEditarSubproducto',
				controllerAs : 'subproductoc',
				backdrop : 'static',
				size : 'md',
				resolve : {
					idsubproducto: function() {
						return idsubproducto;
					},
					index: function(){
						return index;
					}
				}
			});

			modalInstance.result.then(function(resultado) {
				if (resultado != undefined){
					$scope.items[index].content = resultado.nombre;
					$utilidades.mensaje('success','Item modificado con éxito');
				}else{
					$utilidades.mensaje('danger', 'Error al guardar el item de subproducto');
				}

			}, function() {
			});
		};
		  
		mi.editarActividad = function(idactividad,index) {

				var modalInstance = $uibModal.open({
					animation : 'true',
					ariaLabelledBy : 'modal-title',
					ariaDescribedBy : 'modal-body',
					templateUrl : 'editarActividad.jsp',
					controller : 'modalEditarActividad',
					controllerAs : 'actividadc',
					backdrop : 'static',
					size : 'md',
					resolve : {
						index : function() {
							return index;
						},
						idactividad: function() {
							return idactividad;
						}
					}
				});

				modalInstance.result.then(function(resultado) {
					if (resultado != undefined){
						$scope.items[index].content = resultado.nombre;
						$scope.items[index].start = moment(resultado.fechaInicio,'DD/MM/YYYY hh:mm:ss').toDate();
						$scope.items[index].finish = mi.sumarDias($scope.items[index].start,resultado.duracion); 
						$scope.items[index].duration = Number(resultado.duracion);
						mi.reconfigurarFechas($scope.items[index],(index +1),Number($scope.items[index].duracion), $scope.items[index].start);
						$utilidades.mensaje('success','Item modificado con éxito');
					}else{
						$utilidades.mensaje('danger', 'Error al guardar el item de actividad');
					}
				}, function() {
				});
		};
		
		mi.sumarDias = function(fecha, dias){
			var cnt = 0;
		    var tmpDate = moment(fecha);
		    while (cnt < dias) {
		        tmpDate = tmpDate.add(1,'days');
		        if (tmpDate.weekday() != moment().day("Sunday").weekday() && tmpDate.weekday() != moment().day("Saturday").weekday()) {
		            cnt = cnt + 1;
		        }
		    }
		    tmpDate = moment(tmpDate,'DD/MM/YYYY').toDate();
		    return tmpDate;
		}
		
		mi.reconfigurarFechas = function(objeto,index,duracion,fechaInicial){
			
			for(var i=index; i< $scope.items.length; i++){
				if ($scope.items[i].predecessors != null || $scope.items[i].predecessors != undefined){
					var temp = $scope.items[i].predecessors[0].item;
					console.log(temp);
					if ($scope.items[i].predecessors[0].item.id === objeto.id){
						$scope.items[i].start =  (mi.sumarDias (fechaInicial,duracion));
						duracion = duracion + Number( $scope.items[i].duracion);
						$scope.items[i].finish = (mi.sumarDias (fechaInicial,duracion));
						mi.reconfigurarFechas($scope.items[i],(i +1),duracion,fechaInicial);
					}
				}		
			}
		}
		  
	}// fin function controller

]);



app.directive('customOnChange', function() {
  return {
    restrict: 'A',
    link: function (scope, element, attrs) {
      var onChangeHandler = scope.$eval(attrs.customOnChange);
      element.bind('change', onChangeHandler);
    }
  };
});

app.controller('modalEditarPrestamo', [ '$uibModalInstance',
	'$scope', '$http', '$interval', 'i18nService', 'Utilidades',
	'$timeout', '$log', '$uibModal', '$q', 'idprestamo', modalEditarPrestamo ]);

function modalEditarPrestamo($uibModalInstance, $scope, $http, $interval,
	i18nService, $utilidades, $timeout, $log, $uibModal, $q, idprestamo) {

	var mi = this;
	mi.proyecto = {};
	
	$http.post('/SProyecto',{ accion: 'getProyectoPorId', id:idprestamo,t:moment().unix()
	  }).then(

	function(response) {
		mi.proyecto = response.data.proyecto;
		
		mi.poryectotipoid = mi.proyecto.proyectotipoid;
		mi.proyectotiponombre=mi.proyecto.proyectotipo;
		mi.unidadejecutoraid=mi.proyecto.unidadejecutoraid;
		mi.unidadejecutoranombre=mi.proyecto.unidadejecutora;
		mi.cooperanteid=mi.proyecto.cooperanteid;
		mi.cooperantenombre=mi.proyecto.cooperante;
	});
	
	mi.llamarModalBusqueda = function(servlet, accionServlet, datosCarga,columnaId,columnaNombre) {
		var resultado = $q.defer();
		var modalInstance = $uibModal.open({
			animation : 'true',
			ariaLabelledBy : 'modal-title',
			ariaDescribedBy : 'modal-body',
			templateUrl : 'buscarPorProyecto.jsp',
			controller : 'buscarPorProyecto',
			controllerAs : 'modalBuscar',
			backdrop : 'static',
			size : 'md',
			resolve : {
				$servlet : function() {
					return servlet;
				},
				$accionServlet : function() {
					return accionServlet;
				},
				$datosCarga : function() {
					return datosCarga;
				},
				$columnaId : function() {
					return columnaId;
				},
				$columnaNombre : function() {
					return columnaNombre;
				}
			}
		});

		modalInstance.result.then(function(itemSeleccionado) {
			resultado.resolve(itemSeleccionado);
		});
		return resultado.promise;
};
	
	mi.buscarProyectoTipo = function() {
		var resultado = mi.llamarModalBusqueda('/SProyectoTipo', {
			accion : 'numeroProyectoTipos'
		}, function(pagina, elementosPorPagina) {
			return {
				accion : 'getProyectoTipoPagina',
				pagina : pagina,
				numeroproyectotipo : elementosPorPagina
			};
		},'id','nombre');

		resultado.then(function(itemSeleccionado) {
			mi.poryectotipoid= itemSeleccionado.id;
			mi.proyectotiponombre = itemSeleccionado.nombre;			
		});
	};
	
	mi.buscarUnidadEjecutora = function() {
		var resultado = mi.llamarModalBusqueda('/SUnidadEjecutora', {
			accion : 'totalElementos'
		}, function(pagina, elementosPorPagina) {
			return {
				accion : 'cargar',
				pagina : pagina,
				registros : elementosPorPagina
			};
		},'unidadEjecutora','nombreUnidadEjecutora');

		resultado.then(function(itemSeleccionado) {
			mi.unidadejecutoraid= itemSeleccionado.unidadEjecutora;
			mi.unidadejecutoranombre = itemSeleccionado.nombreUnidadEjecutora;
		});
	};

	mi.buscarCooperante = function(prestamo) {
		var resultado = mi.llamarModalBusqueda('/SCooperante', {
			accion : 'numeroCooperantes', t:moment().unix()
		}, function(pagina, elementosPorPagina) {
			return {
				accion : 'getCooperantesPagina',
				pagina : pagina,
				numerocooperantes : elementosPorPagina
			};
		},'id','nombre');

		resultado.then(function(itemSeleccionado) {
			if (prestamo){
				mi.prestamo.cooperanteid= itemSeleccionado.id;
				mi.prestamo.cooperantenombre = itemSeleccionado.nombre;
			}
			else{
				mi.cooperanteid= itemSeleccionado.id;
				mi.cooperantenombre = itemSeleccionado.nombre;
			}
		});
		
		mi.buscarCooperante = function(prestamo) {
			var resultado = mi.llamarModalBusqueda('/SCooperante', {
				accion : 'numeroCooperantes', t:moment().unix()
			}, function(pagina, elementosPorPagina) {
				return {
					accion : 'getCooperantesPagina',
					pagina : pagina,
					numerocooperantes : elementosPorPagina
				};
			},'id','nombre');

			resultado.then(function(itemSeleccionado) {
				if (prestamo){
					mi.prestamo.cooperanteid= itemSeleccionado.id;
					mi.prestamo.cooperantenombre = itemSeleccionado.nombre;
				}
				else{
					mi.cooperanteid= itemSeleccionado.id;
					mi.cooperantenombre = itemSeleccionado.nombre;
				}
			});
		};
	};
	
	mi.ok = function() {
		var param_data = {
				accion : 'guardarModal',
				id: mi.proyecto.id,
				nombre: mi.proyecto.nombre,
				proyectotipoid: mi.poryectotipoid,
				unidadejecutoraid: mi.unidadejecutoraid,
				cooperanteid: mi.cooperanteid,
				esnuevo: false,
				t:moment().unix()
			};
			$http.post('/SProyecto',param_data).then(
				function(response) {
					if (response.data.success) {
						$uibModalInstance.close(response.data.proyecto);
					}else
						$uibModalInstance.close(undefined);
			});
	};

	mi.cancel = function() {
		$uibModalInstance.dismiss('cancel');
	};
}


app.controller('modalEditarComponente', [ '$uibModalInstance',
	'$scope', '$http', '$interval', 'i18nService', 'Utilidades',
	'$timeout', '$log', '$uibModal', '$q', 'idcomponente', modalEditarComponente ]);

function modalEditarComponente($uibModalInstance, $scope, $http, $interval,
	i18nService, $utilidades, $timeout, $log, $uibModal, $q, idcomponente) {

	var mi = this;
	mi.componente = {};
	
	$http.post('/SComponente',{ accion: 'getComponentePorId', id:idcomponente,t:moment().unix()
	  }).then(

	function(response) {
		if (response.data.componente!=null){
			mi.componente = response.data.componente;
			mi.unidadejecutoraid= mi.componente.unidadejecutoraid;
			mi.unidadejecutoranombre= mi.componente.unidadejecutoranombre;
			mi.componentetipoid=mi.componente.componentetipoid;
			mi.componentetiponombre=mi.componente.componentetiponombre;
		}
	});
	
	mi.llamarModalBusqueda = function(servlet, accionServlet, datosCarga,columnaId,columnaNombre) {
		var resultado = $q.defer();
		var modalInstance = $uibModal.open({
			animation : 'true',
			ariaLabelledBy : 'modal-title',
			ariaDescribedBy : 'modal-body',
			templateUrl : 'buscarPorProyecto.jsp',
			controller : 'buscarPorProyecto',
			controllerAs : 'modalBuscar',
			backdrop : 'static',
			size : 'md',
			resolve : {
				$servlet : function() {
					return servlet;
				},
				$accionServlet : function() {
					return accionServlet;
				},
				$datosCarga : function() {
					return datosCarga;
				},
				$columnaId : function() {
					return columnaId;
				},
				$columnaNombre : function() {
					return columnaNombre;
				}
			}
		});

		modalInstance.result.then(function(itemSeleccionado) {
			resultado.resolve(itemSeleccionado);
		});
		return resultado.promise;
	};


	mi.buscarComponenteTipo = function() {
		var resultado = mi.llamarModalBusqueda('/SComponenteTipo', {
			accion : 'numeroComponenteTipos'
		}, function(pagina, elementosPorPagina) {
			return {
				accion : 'getComponentetiposPagina',
				pagina : pagina,
				numerocomponentetipos : elementosPorPagina
			};
		},'id','nombre');

		resultado.then(function(itemSeleccionado) {
			mi.componentetipoid = itemSeleccionado.id;
			mi.componentetiponombre = itemSeleccionado.nombre;
		});
	};

	mi.buscarUnidadEjecutora = function() {
		var resultado = mi.llamarModalBusqueda('/SUnidadEjecutora', {
			accion : 'totalElementos'
		}, function(pagina, elementosPorPagina) {
			return {
				accion : 'cargar',
				pagina : pagina,
				registros : elementosPorPagina
			};
		},'unidadEjecutora','nombreUnidadEjecutora');

		resultado.then(function(itemSeleccionado) {
			mi.unidadejecutoraid = itemSeleccionado.unidadEjecutora;
			mi.unidadejecutoranombre = itemSeleccionado.nombreUnidadEjecutora;
		});
	};
	
	mi.ok = function() {
		var param_data = {
				accion : 'guardarModal',
				componentetipoid : mi.componentetipoid,
				id: mi.componente.id,
				nombre: mi.componente.nombre,
				unidadejecutoraid:mi.unidadejecutoraid,
				esnuevo: false,
				t:moment().unix()
			};
			$http.post('/SComponente',param_data).then(
				function(response) {
					if (response.data.success) {
						$uibModalInstance.close(response.data.componente);
					}else
						$uibModalInstance.close(undefined);
			});
	};

	mi.cancel = function() {
		$uibModalInstance.dismiss('cancel');
	};
}


app.controller('modalEditarProducto', [ '$uibModalInstance',
	'$scope', '$http', '$interval', 'i18nService', 'Utilidades',
	'$timeout', '$log', '$uibModal', '$q', 'idproducto', modalEditarProducto ]);

function modalEditarProducto($uibModalInstance, $scope, $http, $interval,
	i18nService, $utilidades, $timeout, $log, $uibModal, $q, idproducto) {

	var mi = this;
	mi.componente = {};
	
	$http.post('/SProducto',{ accion: 'getProductoPorId', id:idproducto,t:moment().unix()
	  }).then(

	function(response) {
		if (response.data.producto!=null){
			mi.producto = response.data.producto;
			mi.productoPadreNombre = mi.producto.producto;
			mi.unidadEjecutora = mi.producto.unidadEjectuora;
			mi.unidadEjecutoraNombre = mi.producto.nombreUnidadEjecutora;
			mi.tipo = mi.producto.idProductoTipo;
			mi.tipoNombre = mi.producto.productoTipo;
		}
	});
	
	mi.llamarModalBusqueda = function(servlet, accionServlet, datosCarga,columnaId,columnaNombre) {
		var resultado = $q.defer();
		var modalInstance = $uibModal.open({
			animation : 'true',
			ariaLabelledBy : 'modal-title',
			ariaDescribedBy : 'modal-body',
			templateUrl : 'buscarPorProyecto.jsp',
			controller : 'buscarPorProyecto',
			controllerAs : 'modalBuscar',
			backdrop : 'static',
			size : 'md',
			resolve : {
				$servlet : function() {
					return servlet;
				},
				$accionServlet : function() {
					return accionServlet;
				},
				$datosCarga : function() {
					return datosCarga;
				},
				$columnaId : function() {
					return columnaId;
				},
				$columnaNombre : function() {
					return columnaNombre;
				}
			}
		});

		modalInstance.result.then(function(itemSeleccionado) {
			resultado.resolve(itemSeleccionado);
		});
		return resultado.promise;
	};

	mi.buscarTipo = function() {
		var resultado = mi.llamarModalBusqueda('/SProductoTipo', {
			accion : 'totalElementos'
		}, function(pagina, elementosPorPagina) {
			return {
				accion : 'cargar',
				pagina : pagina,
				registros : elementosPorPagina
			};
		},'id','nombre');

		resultado.then(function(itemSeleccionado) {
			mi.tipo = itemSeleccionado.id;
			mi.tipoNombre = itemSeleccionado.nombre;
		});

	};
	
	mi.buscarUnidadEjecutora = function() {
		var resultado = mi.llamarModalBusqueda('/SUnidadEjecutora', {
			accion : 'totalElementos'
		}, function(pagina, elementosPorPagina) {
			return {
				accion : 'cargar',
				pagina : pagina,
				registros : elementosPorPagina
			};
		},'unidadEjecutora','nombreUnidadEjecutora');

		resultado.then(function(itemSeleccionado) {
			mi.unidadEjecutora = itemSeleccionado.unidadEjecutora;
			mi.unidadEjecutoraNombre = itemSeleccionado.nombreUnidadEjecutora;
		});
	};
	
	mi.ok = function() {
		var param_data = {
				accion : 'guardarModal',
				id: mi.producto.id,
				nombre : mi.producto.nombre,
				tipoproductoid : mi.tipo,
				unidadEjecutora : mi.unidadEjecutora,
				t:moment().unix()
			};
			$http.post('/SProducto',param_data).then(
				function(response) {
					if (response.data.success) {
						$uibModalInstance.close(response.data.producto);
					}else
						$uibModalInstance.close(undefined);
			});
	};

	mi.cancel = function() {
		$uibModalInstance.dismiss('cancel');
	};
}


app.controller('modalEditarSubproducto', [ '$uibModalInstance',
	'$scope', '$http', '$interval', 'i18nService', 'Utilidades',
	'$timeout', '$log', '$uibModal', '$q', 'idsubproducto', modalEditarSubproducto ]);

function modalEditarSubproducto($uibModalInstance, $scope, $http, $interval,
	i18nService, $utilidades, $timeout, $log, $uibModal, $q, idsubproducto) {

	var mi = this;
	mi.componente = {};
	
	$http.post('/SSubproducto',{ accion: 'getSubproductoPorId', id:idsubproducto,t:moment().unix()
	  }).then(

	function(response) {
		if (response.data.subproducto!=null){
			mi.subproducto = response.data.subproducto;
			mi.tipo = mi.subproducto.idSubproductoTipo;
			mi.tipoNombre = mi.subproducto.subproductoTipo;
			
			mi.unidadEjecutora = mi.subproducto.unidadEjectuora;
			mi.unidadEjecutoraNombre = mi.subproducto.nombreUnidadEjecutora;
		}
	});
	
	mi.llamarModalBusqueda = function(servlet, accionServlet, datosCarga,columnaId,columnaNombre) {
		var resultado = $q.defer();
		var modalInstance = $uibModal.open({
			animation : 'true',
			ariaLabelledBy : 'modal-title',
			ariaDescribedBy : 'modal-body',
			templateUrl : 'buscarPorProyecto.jsp',
			controller : 'buscarPorProyecto',
			controllerAs : 'modalBuscar',
			backdrop : 'static',
			size : 'md',
			resolve : {
				$servlet : function() {
					return servlet;
				},
				$accionServlet : function() {
					return accionServlet;
				},
				$datosCarga : function() {
					return datosCarga;
				},
				$columnaId : function() {
					return columnaId;
				},
				$columnaNombre : function() {
					return columnaNombre;
				}
			}
		});

		modalInstance.result.then(function(itemSeleccionado) {
			resultado.resolve(itemSeleccionado);
		});
		return resultado.promise;
	};

	mi.buscarTipo = function() {
		var resultado = mi.llamarModalBusqueda('/SSubproductoTipo', {
			accion : 'totalElementos'
		}, function(pagina, elementosPorPagina) {
			return {
				accion : 'cargar',
				pagina : pagina,
				registros : elementosPorPagina
			};
		},'id','nombre');

		resultado.then(function(itemSeleccionado) {
			mi.tipo = itemSeleccionado.id;
			mi.tipoNombre = itemSeleccionado.nombre;
			
			var parametros = { 
				accion: 'getSubproductoPropiedadPorTipo', 
				idsubproducto: mi.subproducto.id,
				idsubproductotipo: itemSeleccionado.id
			}
			$http.post('/SSubproductoPropiedad', parametros).then(function(response){
				mi.camposdinamicos = response.data.subproductopropiedades;
				for (campos in mi.camposdinamicos) {
					switch (mi.camposdinamicos[campos].tipo){
						case "fecha":
							mi.camposdinamicos[campos].valor = (mi.camposdinamicos[campos].valor!='') ? moment(mi.camposdinamicos[campos].valor,'DD/MM/YYYY').toDate() : null;
							break;
						case "entero":
							mi.camposdinamicos[campos].valor = Number(mi.camposdinamicos[campos].valor);
							break;
						case "decimal":
							mi.camposdinamicos[campos].valor = Number(mi.camposdinamicos[campos].valor);
							break;
					}
				}
				
			});
		});

	};
	
	mi.buscarUnidadEjecutora = function() {
		var resultado = mi.llamarModalBusqueda('/SUnidadEjecutora', {
			accion : 'totalElementos'
		}, function(pagina, elementosPorPagina) {
			return {
				accion : 'cargar',
				pagina : pagina,
				registros : elementosPorPagina
			};
		},'unidadEjecutora','nombreUnidadEjecutora');

		resultado.then(function(itemSeleccionado) {
			mi.unidadEjecutora = itemSeleccionado.unidadEjecutora;
			mi.unidadEjecutoraNombre = itemSeleccionado.nombreUnidadEjecutora;
		});
	};
	
	mi.ok = function() {
		var param_data = {
				accion : 'guardarModal',
				id: mi.subproducto.id,
				nombre : mi.subproducto.nombre,
				tiposubproductoid : mi.tipo,
				unidadEjecutora : mi.unidadEjecutora,
				t:moment().unix()
			};
			$http.post('/SSubproducto',param_data).then(
				function(response) {
					if (response.data.success) {
						$uibModalInstance.close(response.data.subproducto);
					}else
						$uibModalInstance.close(undefined);
			});
	};

	mi.cancel = function() {
		$uibModalInstance.dismiss('cancel');
	};
}


app.controller('modalEditarActividad', [ '$uibModalInstance',
	'$scope', '$http', '$interval', 'i18nService', 'Utilidades',
	'$timeout', '$log',   '$uibModal', '$q' ,'idactividad',modalEditarActividad ]);

function modalEditarActividad($uibModalInstance, $scope, $http, $interval,
	i18nService, $utilidades, $timeout, $log, $uibModal, $q,idactividad) {

	var mi = this;
	mi.actividad = {};
	mi.primeraActividad=false;
	
	mi.dimensiones = [{id:1,nombre:'Dias',sigla:'d'}];
	
	mi.formatofecha = 'dd/MM/yyyy';
	
	mi.fechaOptions = {
			formatYear : 'yy',
			maxDate : new Date(2050, 12, 31),
			minDate : new Date(1990, 1, 1),
			startingDay : 1
	};

	mi.ff_opciones = {
			formatYear : 'yy',
			maxDate : new Date(2050, 12, 31),
			minDate : new Date(1990, 1, 1),
			startingDay : 1
	};
	
	mi.abrirPopupFecha = function(index) {
		if(index > 0 && index<1000){
			mi.camposdinamicos[index].isOpen = true;
		}
		else{
			switch(index){
				case 1000: mi.fi_abierto = true; break;
			}
		}
	};
	
	$http.post('/SActividad',{ accion: 'getActividadPorId', id:idactividad,t:moment().unix()
	  }).then(

		function(response) {
			mi.actividad = response.data.actividad;
			mi.actividad.fechaInicio = moment(mi.actividad.fechaInicio,'DD/MM/YYYY').toDate();
			mi.actividad.fechaFin = moment(mi.actividad.fechaFin,'DD/MM/YYYY').toDate();
			mi.ff_opciones.minDate = mi.actividad.fechaInicio;
			mi.duracionDimension = {
					"id": mi.actividad.duracionDimension === 'd' ? 1 : 0,
					"nombre": mi.actividad.duracionDimension,
					"sigla": 'd'
			};
			mi.primeraActividad = mi.actividad.prececesorId == undefined 
				|| mi.actividad.prececesorId == null ? true : false;
	});
	
	mi.cambioDuracion = function(){
		mi.actividad.fechaFin = mi.sumarDias(mi.actividad.fechaInicio,mi.actividad.duracion);
	}
	
	mi.sumarDias = function(fecha, dias){
		var cnt = 0;
	    var tmpDate = moment(fecha);
	    while (cnt < dias) {
	        tmpDate = tmpDate.add(1,'days');
	        if (tmpDate.weekday() != moment().day("Sunday").weekday() && tmpDate.weekday() != moment().day("Saturday").weekday()) {
	            cnt = cnt + 1;
	        }
	    }
	    tmpDate = moment(tmpDate,'DD/MM/YYYY').toDate();
	    return tmpDate;
	}
	
	
	mi.llamarModalBusqueda = function(servlet, accionServlet, datosCarga,columnaId,columnaNombre) {
		var resultado = $q.defer();
		var modalInstance = $uibModal.open({
			animation : 'true',
			ariaLabelledBy : 'modal-title',
			ariaDescribedBy : 'modal-body',
			templateUrl : 'buscarPorProyecto.jsp',
			controller : 'buscarPorProyecto',
			controllerAs : 'modalBuscar',
			backdrop : 'static',
			size : 'md',
			resolve : {
				$servlet : function() {
					return servlet;
				},
				$accionServlet : function() {
					return accionServlet;
				},
				$datosCarga : function() {
					return datosCarga;
				},
				$columnaId : function() {
					return columnaId;
				},
				$columnaNombre : function() {
					return columnaNombre;
				}
			}
		});

		modalInstance.result.then(function(itemSeleccionado) {
			resultado.resolve(itemSeleccionado);
		});
		return resultado.promise;
	};

	mi.buscarTipo = function() {
		var resultado = mi.llamarModalBusqueda('/SActividadTipo', {
			accion : 'numeroActividadTipos'
		}, function(pagina, elementosPorPagina) {
			return {
				accion : 'getActividadtiposPagina',
				pagina : pagina,
				registros : elementosPorPagina
			};
		},'id','nombre');

		resultado.then(function(itemSeleccionado) {
			mi.actividad.actividadtipoid = itemSeleccionado.id;
			mi.actividad.actividadtiponombre = itemSeleccionado.nombre;
		});

	};
	
	
	
	mi.ok = function() {
		var param_data = {
				accion : 'guardarModal',
				actividadtipoid : mi.actividad.actividadtipoid,
				id: mi.actividad.id,
				nombre: mi.actividad.nombre,
				descripcion: mi.actividad.descripcion,
				fechainicio: moment(mi.actividad.fechaInicio).format('DD/MM/YYYY'),
				fechafin: moment(mi.actividad.fechaFin).format('DD/MM/YYYY'),
				porcentajeavance: mi.actividad.porcentajeavance,
				duracion:mi.actividad.duracion,
				duracionDimension:mi.duracionDimension.sigla,
				t:moment().unix()
			};
			$http.post('/SActividad',param_data).then(
				function(response) {
					if (response.data.success) {
						$uibModalInstance.close(response.data.actividad);
					}else
						$uibModalInstance.close(undefined);
			});
	};

	mi.cancel = function() {
		$uibModalInstance.dismiss('cancel');
	};


}

app.controller('buscarPorProyecto', [ '$uibModalInstance',
	'$scope', '$http', '$interval', 'i18nService', 'Utilidades',
	'$timeout', '$log', '$servlet', '$accionServlet', '$datosCarga',
	'$columnaId','$columnaNombre',buscarPorProyecto ]);

function buscarPorProyecto($uibModalInstance, $scope, $http, $interval,
	i18nService, $utilidades, $timeout, $log, $servlet,$accionServlet,$datosCarga,$columnaId,$columnaNombre) {

	var mi = this;

	mi.totalElementos = 0;
	mi.paginaActual = 1;
	mi.numeroMaximoPaginas = 5;
	mi.elementosPorPagina = 9;

	mi.mostrarCargando = false;
	mi.data = [];

	mi.itemSeleccionado = null;
	mi.seleccionado = false;

	$http.post($servlet, $accionServlet).success(function(response) {
		for ( var key in response) {
			mi.totalElementos = response[key];
		}
		mi.cargarData(1);
	});

	mi.opcionesGrid = {
		data : mi.data,
		columnDefs : [ {
			displayName : 'ID',
			name : $columnaId,
			cellClass : 'grid-align-right',
			type : 'number',
			width : 70
		}, {
			displayName : 'Nombre',
			name : $columnaNombre,
			cellClass : 'grid-align-left'
		} ],
		enableRowSelection : true,
		enableRowHeaderSelection : false,
		multiSelect : false,
		modifierKeysToMultiSelect : false,
		noUnselect : false,
		enableFiltering : true,
		enablePaginationControls : false,
		paginationPageSize : 5,
		onRegisterApi : function(gridApi) {
			mi.gridApi = gridApi;
			mi.gridApi.selection.on.rowSelectionChanged($scope,
					mi.seleccionarTipoRiesgo);
		}
	}

	mi.seleccionarTipoRiesgo = function(row) {
		mi.itemSeleccionado = row.entity;
		mi.seleccionado = row.isSelected;
	};

	mi.cargarData = function(pagina) {
		mi.mostrarCargando = true;
		$http.post($servlet, $datosCarga(pagina, mi.elementosPorPagina)).then(
				function(response) {
					if (response.data.success) {

						for ( var key in response.data) {
							if (key != 'success')
								mi.data = response.data[key];
						}
						mi.opcionesGrid.data = mi.data;

						mi.mostrarCargando = false;
					}
				});
	};

	mi.cambioPagina = function() {
		mi.cargarData(mi.paginaActual);
	}

	mi.ok = function() {
		if (mi.seleccionado) {
			$uibModalInstance.close(mi.itemSeleccionado);
		} else {
			$utilidades.mensaje('warning', 'Debe seleccionar una fila');
		}
	};

	mi.cancel = function() {
		$uibModalInstance.dismiss('cancel');
	};
}




