<html>
    <head>
        <meta name="description" content="app, web app, responsive, admin dashboard, admin, flat, flat ui, ui kit, off screen nav" />
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />
        <link href="${pageContext.request.contextPath}/js/jtable.2.3.1/themes/metro/crimson/jtable.css" rel="stylesheet" type="text/css" />
        <link href="${pageContext.request.contextPath}/css/jquery-ui-1.10.3.custom.css" rel="stylesheet" type="text/css" />
        <script src="${pageContext.request.contextPath}/js/jquery-2.0.3.min.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/js/jquery-ui-1.10.3.custom.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/js/jquery.jtable.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/js/jquery.hotkeys-0.7.9.min.js" type="text/javascript"></script>
        <link href="${pageContext.request.contextPath}/js/validationEngine/validationEngine.jquery.css" rel="stylesheet" type="text/css" />
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/validationEngine/jquery.validationEngine.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/validationEngine/jquery.validationEngine-es.js"></script>
    </head>
    <body>

        <div id="Usuarios" style="margin:auto;width:95%;">
            <div class="filtering">
                Buscar <input type="text" id="buscar">
            </div>
            <button id="DeleteAllButton" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only" role="button" aria-disabled="false"><span class="ui-button-text">
    Delete all selected records</span></button>
        </div>
        <script type="text/javascript">
            $(document).ready(function () {
                $('#Usuarios').jtable({
                    title: 'Usuarios',
                    paging: true,
                    pageSize: 5,
                    sorting: true,
                    defaultSorting: 'Nombre ASC',
                    toolbarsearch: true,
                    selecting: true,
                    multiselect: true,
                    selectingCheckboxes: true,
                    openChildAsAccordion: true,
                    actions: {
                        listAction: '${pageContext.request.contextPath}/Control?action=list',
                        createAction: '${pageContext.request.contextPath}/Control?action=create',
                        updateAction: '${pageContext.request.contextPath}/Control?action=update',
                        deleteAction: '${pageContext.request.contextPath}/Control?action=delete'
                    },
                    fields: {
                        IdUsuario: {
                            key: true,
                            create: false,
                            edit: false,
                            list: false
                        },
                        contact:{
						title:'',
						
						sorting: true,
						edit: true,
						create: true,
						display: function (cochesData){
							//Creacion de imagen
							var $img=$('<img src="${pageContext.request.contextPath}/js/submenu.png" title="Editar Mantenimiento"/>');
							//Abrir al click
							$img.click(function () {
								//alert("Hola");
                            $('#Usuarios').jtable('openChildTable',
                                    $img.closest('tr'), //Parent row
                                    {
                                    title: cochesData.record.modelo,
                                    actions: {
                                        listAction: '${pageContext.request.contextPath}/Control?action=listContact',
                        createAction: '${pageContext.request.contextPath}/Control?action=createContact',
                        updateAction: '${pageContext.request.contextPath}/Control?action=updateContact',
                        deleteAction: '${pageContext.request.contextPath}/Control?action=deleteContact'
                                    },
                                    fields: {
                                        IdUsuario: {
                                            type: 'hidden',
                                            defaultValue: cochesData.record.IdUsuario
                                        },
						
						
                                        IdData: {
                                            key: true,
                                            create: false,
                                            edit: false,
                                            list: false
                                        },
                                        Direccion: {
                                            title: 'Direccion'
                                        },
                                        Telefono: {
                                            title: 'Telefono'
                                        },
                                        Email: {
                                            title: 'Email'

                                        }									
                                    }
                                }, function (cochesdata) { //opened handler
                                    cochesdata.childTable.jtable('load');
                                });
                        });
							//Return image to show on the person row
							return $img;

						}//end coches data
					},
                                        
                                        
                                        
                                        
                                        relatives:{
						title:'Relativos',
						width:'15%',
						sorting: false,
						edit: false,
						create: false,
						display: function (mecData){
							//Creacion de imagen
							var $ima=$('<img src=${pageContext.request.contextPath}/js/"submenu.png" title="Relatives"/>');
							//Abrir al click
							$ima.click(function () {
								//alert("Hola");
                            $('#Usuarios').jtable('openChildTable',
                                    $ima.closest('tr'), //Parent row
                                    {
                                    title: mecData.record.telefono,
                                    actions: {
                                        listAction: '${pageContext.request.contextPath}/Control?action=listRelatives',
                        createAction: '${pageContext.request.contextPath}/Control?action=createRelatives',
                        updateAction: '${pageContext.request.contextPath}/Control?action=updateRelatives',
                        deleteAction: '${pageContext.request.contextPath}/Control?action=deleteRelatives'
                                    },
                                    fields: {
                                        IdRelative: {
                                            type: 'hidden',
                                            defaultValue: mecData.record.idman
                                        },
                                        IdUsuario: {
                                            key: true,
                                            create: false,
                                            edit: false,
                                            list: false
                                        },
                                        Parentesco: {
                                            title: 'Parentesco',
                                            width: '25%'
                                        },
                                        Nombre: {
                                            title: 'Nombre',
                                            width: '50%'
                                        },                                        
					EmailR:{
											title:'EmailR'
											
										}
                                    }
                                }, function (datamec) { //opened handler
                                    datamec.childTable.jtable('load');
                                });
                        });
							//Return image to show on the person row
							return $ima;

						}//end coches data
					},//final de la hija, de la hija
                                        
                                        
                        Usuario: {
                            title: 'Usuario '
                        },
                        Password: {
                            title: 'Password'
                        },
                        Nombre: {
                            title: 'Nombre'
                        }
                    }
                });
                
                            
                
                $('#Usuarios').jtable('load');
               

            });
            
        </script>

    </body>
</html>
