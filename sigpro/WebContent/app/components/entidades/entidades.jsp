<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="org.apache.shiro.SecurityUtils" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<div ng-controller="controlEntidad as entidad" class="maincontainer all_page">

  <h3>{{ entidad.esForma ? (entidad.esNuevo ? "Nueva Entidad" : "Editar Entidad") : "Entidades" }}</h3>

  <br />

  <div class="row" align="center" ng-hide="entidad.esForma">
    <div class="col-sm-12 operation_buttons" align="right">
      <div class="btn-group">
      	<shiro:hasPermission name="1040">
      		<label class="btn btn-primary" ng-click="entidad.nueva()">Nueva</label>
      	</shiro:hasPermission>
      	<shiro:hasPermission name="1020">
        	<label class="btn btn-primary" ng-click="entidad.editar()">Editar</label>
      	</shiro:hasPermission>
      </div>
    </div>
    <shiro:hasPermission name="10010">
    	  <div class="col-sm-12" align="center">
	      <div style="height: 35px;">
			<div style="text-align: right;">

				<div class="btn-group" role="group" aria-label="">
					<a class="btn btn-default" href ng-click="entidad.reiniciarVista()" role="button" uib-tooltip="Reiniciar la vista de la tabla" tooltip-placement="left"><span class="glyphicon glyphicon-repeat" aria-hidden="true"></span></a>
				</div>
			</div>
		  </div>
		  <br/>
	      <div id="grid1" ui-grid="entidad.entidades_gridOptions" ui-grid-save-state ui-grid-move-columns ui-grid-resize-columns ui-grid-selection ui-grid-pinning ui-grid-pagination>
	        <div class="grid_loading" ng-hide="!entidad.mostrarCargando">
	          <div class="msg">
	            <span><i class="fa fa-spinner fa-spin fa-4x"></i> <br /> <br /> <b>Cargando, por favor espere...</b> </span>
	          </div>
	        </div>
	      </div>
	      <ul uib-pagination
	        total-items="entidad.totalEntidades"
	        ng-model="entidad.paginaActual"
	        max-size="entidad.numeroMaximoPaginas"
	        items-per-page="entidad.elementosPorPagina"
	        first-text="Primero"
	        last-text="Último"
	        next-text="Siguiente"
	        previous-text="Anterior"
	        class="pagination-sm"
	        boundary-links="true"
	        force-ellipses="true"
	        ng-change="entidad.cambioPagina()"
	      ></ul>
	    </div>
	</shiro:hasPermission>

  </div>

  <div class="row main-form" ng-show="entidad.esForma">

    <div class="col-sm-12 operation_buttons" align="right">

      <div class="btn-group">
        <label class="btn btn-success" ng-click="form.$valid ? entidad.guardar() : ''" ng-disabled="!form.$valid">Guardar</label>
        <label class="btn btn-primary" ng-click="entidad.cancelar()">Ir a Tabla</label>
      </div>

    </div>

    <div>

      <form name="form" class="css-form" novalidate>

			<div class="row">
		        <div class="form-group col-sm-12">
		          <label>* Entidad</label>
		          <input type="number" class="form-control" placeholder="Código Entidad" ng-model="entidad.entidad.entidad" ng-readonly="!entidad.esNuevo" ng-required="true" />
		        </div>
		    </div>

			<div class="row">
				<div class="form-group col-sm-12" >
				  <label for="campo2">* Nombre Entidad</label>
				  <input type="text" class="form-control" id="campo2" name="campo2" placeholder="Nombre Entidad" ng-model="entidad.entidad.nombre" ng-readonly="!entidad.esNuevo" ng-required="true" />
				</div>
			</div>
			<div class="row">
		        <div class="form-group col-sm-12">
		          <label for="campo3">Abreviatura</label>
		          <input type="text" class="form-control" placeholder="Abreviatura" ng-model="entidad.entidad.abreviatura">
		        </div>
			</div>
      </form>

    </div>
    <div align="center">Los campos marcados con * son obligatorios</div>

    <div class="col-sm-12 operation_buttons" align="right">
      <div class="btn-group">
        <label class="btn btn-success" ng-click="form.$valid ? entidad.guardar() : ''" ng-disabled="!form.$valid">Guardar</label>
        <label class="btn btn-primary" ng-click="entidad.cancelar()">Ir a Tabla</label>
      </div>
    </div>
  </div>

</div>
