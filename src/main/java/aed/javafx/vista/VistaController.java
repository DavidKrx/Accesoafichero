package aed.javafx.vista;

import javafx.fxml.Initializable;

import java.util.List;
import java.awt.CheckboxGroup;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.AnchorPane;

public class VistaController implements Initializable{

		//Model
		//af
		private StringProperty rutaaf = new SimpleStringProperty();
		private StringProperty rutaafcwd=new SimpleStringProperty();
		private StringProperty af_contenido=new SimpleStringProperty();
		//aa
		private StringProperty list=new SimpleStringProperty();
		private StringProperty rutaaa = new SimpleStringProperty();
		private StringProperty rutaaacwd=new SimpleStringProperty();
		private StringProperty aa_contenido=new SimpleStringProperty();
		private StringProperty aa_visuaequipo=new SimpleStringProperty();
		//ax
		private StringProperty rutaax = new SimpleStringProperty();
		private StringProperty ax_otroFile=new SimpleStringProperty();
		private StringProperty ax_contenido=new SimpleStringProperty();		
		private StringProperty ax_nombEqui=new SimpleStringProperty();
		private StringProperty ax_fut=new SimpleStringProperty();
		private StringProperty ax_FechIn=new SimpleStringProperty();
		private StringProperty ax_FechFin=new SimpleStringProperty();
		private StringProperty ax_Copas=new SimpleStringProperty();
		//Vista
	 	@FXML
	    private AnchorPane view;
		//Acceso a fichero
	    @FXML
	    private Tab af;

	    @FXML
	    private TextField af_carpetaFichero;

	    @FXML
	    private TextField af_ruta;
	    
	    @FXML
	    private Button af_butCrear,af_butEliminar,af_butMover, af_butFicheroDirectorio,af_butContfichero, af_butModFichero; 

	    @FXML
	    private CheckboxGroup checl;
	    
	    @FXML
	    private CheckBox af_checBesCarpeta, af_checBesFichero;

	    @FXML
	    private TextArea af_txareaContfichero, af_lvFicheroDirectorio;


	    //Acceso Aleatorio
	    @FXML
	    private Tab aa;

	    @FXML
	    private TextField aa_ruta, aa_id, aa_nombre, aa_liga, aa_localidad, aa_nCopas, aa_nombreFichero, aa_txfEquipo, aa_txfCopas;

	    @FXML
	    private Button aa_crearFichero, aa_butContFichero, aa_butInserDato, aa_butEquipo, aa_butCopas;


	    @FXML
	    private TextArea aa_txareaContFichero, aa_txareaInserDato;
	    //Insertar datos

	    @FXML
	    private CheckBox aa_checBInterna;


	    //Acceso XML
	    @FXML
	    private Tab ax;

	    @FXML
	    private Button ax_butWritOtroFich, ax_butContDirect, ax_butEliminar, 
	    ax_butVerdatos, ax_butAddEquipo, ax_butModCop, ax_butContraEquipo;

	    @FXML
	    private TextArea ax_txareaVerdatos;

	    @FXML
	    private TextField ax_ruta, ax_txfWritOtroFich, ax_txfEliminar, ax_txfAddEquipo, 
	    ax_txfModCop, ax_txfEquipo, ax_txfFechaIni,ax_txfFechaFin, ax_txfFutbolista;

	    //AA
	    @FXML
	    void aaOnbutContFichero(ActionEvent event) throws IOException {
//
	    	String rut = rutaaa.get().concat(rutaaacwd.get());

			File a = new File(rut);
	    	if(a.canRead()){
				FileReader c=new FileReader(a);
				String cadena;
				BufferedReader h=new BufferedReader(c);
				cadena = h.readLine();
				
				
			    aa_contenido.set(cadena);
			    h.close();
	    }}

	    @FXML
	    void aaOnbutCopas(ActionEvent event) {

	    }

	    @FXML
	    void aaOnbutEquipo(ActionEvent event) throws IOException {
	    	Alert alert=new Alert(AlertType.WARNING);
			String rut = rutaaa.get().concat(rutaaacwd.get());
			//if(!rutaaacwd.get().isBlank() & aa_visuaequipo.get().isBlank()){
				
	    	RandomAccessFile file=new RandomAccessFile(rut, "rw");
	    	int capacidad=230;
	    	String capa =aa_visuaequipo.get();
	    	capacidad*=capa.indexOf(0);
	    	if(file.length()==0) {
	    		alert.setContentText("El fichero está vacio");
	    	}else { if (file.length()>capacidad) {
	    		file.seek(capacidad);
	    		String cap=file.toString().substring(0, 230);
	    		aa_contenido.set(cap);
	    	}
	    	
	    	}
	    	/*
	    }else{
	    	 alert.setContentText("Escribre el nombre del fichero a ver");
     		 alert.showAndWait();
	    }*/
			}

	    @FXML
	    void aaOnbutInserDato(ActionEvent event) throws IOException {
	    	Alert alert=new Alert(AlertType.WARNING);
			Alert congrat=new Alert(AlertType.CONFIRMATION);
			if(!aa_nombre.textProperty().get().isBlank() &
					!aa_liga.textProperty().get().isBlank() &
					!aa_localidad.textProperty().get().isBlank() &
					!aa_nCopas.textProperty().get().isBlank()) {
	    	String rut = rutaaa.get().concat(rutaaacwd.get());
	    	String textoRelleno="                                                                                     ";
	    	String internatio = "0";
	    	if(aa_checBInterna.isSelected()) {
	    		internatio="1";
	    	} else {if (!aa_checBInterna.isSelected())
	    	{internatio="0";}
	    	} 
	    	String[] data = new String[4];
			data[0] = (aa_nombre.textProperty().get() + textoRelleno).substring(0,40);
			data[1] = (aa_liga.textProperty().get() + textoRelleno).substring(0, 5);
			data[2] = (aa_localidad.textProperty().get() + textoRelleno).substring(0, 60);
			data[3] = (aa_nCopas.textProperty().get()+ textoRelleno).substring(0, 2);
			
			RandomAccessFile fichero = new RandomAccessFile(rut, "rw");
			int id = 0;
			String sd = null;
			
			if ((fichero.length() == 0)) {
				fichero.seek(fichero.length());
				id=1;
				sd=""+id;
			} else { if(fichero.length() == 230) {
				fichero.seek(fichero.length());
				id=2;
				sd=""+id;
			} else{if(fichero.length()==460) {
			id=3;
			sd=""+id;}
			else{ if(fichero.length()==690) {
				id=4;
				sd=""+id;} else {
			if(fichero.length()==920) {
						id=5;
						sd=""+id;}
				}}}}

			System.out.println(fichero.length());
			fichero.writeChars(sd);									
			fichero.writeChar(',');
			
			fichero.writeChars(data[0]);								
			fichero.writeChar(',');

			fichero.writeChars(data[1]);								
			fichero.writeChar(',');
			
			fichero.writeChars(data[2]);								
			fichero.writeChar(',');
			
			fichero.writeChars(data[3]);				
			fichero.writeChar(',');
			
			fichero.writeChars(internatio);
			fichero.writeChar(',');
		
			fichero.close();
			congrat.setContentText("Se ha incertado");
			congrat.showAndWait();
			}else{
	    	 alert.setContentText("Escribre el nombre del fichero a ver");
     		 alert.showAndWait();
	    }}
	    @FXML
	    void aaOncrearFichero(ActionEvent event) throws IOException {
	    	Alert alert=new Alert(AlertType.WARNING);
			Alert congrat=new Alert(AlertType.CONFIRMATION);
			if(!rutaaacwd.get().isBlank()){
			String rut = rutaaa.get().concat(rutaaacwd.get());

			File a = new File(rut);

			if (a.exists()) {
				alert.setContentText(a.getName() + " ya existe");
				alert.showAndWait();
			} else {	
					a.createNewFile();
					congrat.setContentText(a.getName() + " se ha creado");
					congrat.showAndWait();

	    }}else{alert.setContentText("Introdusca el nombre del fichero");
	    alert.showAndWait();}}
	  //AF
	    @FXML
	    void afOnbutContfichero(ActionEvent event) throws IOException {
	    	if(!rutaafcwd.get().isBlank()){
	    	af_txareaContfichero.setEditable(true);
	    	String rut = rutaaf.get().concat(rutaafcwd.get());

			File a = new File(rut);
	    	if(a.canRead()){
				FileReader c=new FileReader(a);
				String cadena;
			
				BufferedReader h=new BufferedReader(c);
			
				
				cadena = h.readLine();
				
				
			    af_contenido.set(cadena);;
			    h.close();
	    }} else{
	    	Alert alert=new Alert(AlertType.WARNING);
     		 alert.setContentText("Escribre el nombre del fichero a ver");
     		 alert.showAndWait();
     	 }}

	@FXML
	void afOnbutCrear(ActionEvent event) throws IOException {
		Alert alert=new Alert(AlertType.WARNING);
		Alert congrat=new Alert(AlertType.CONFIRMATION);
		if(!rutaafcwd.get().isBlank()){
		String rut = rutaaf.get().concat(rutaafcwd.get());

		File a = new File(rut);
		if (a.exists()) {
			alert.setContentText(a.getName()+" ya existe");
			alert.showAndWait();
		} else {
			if (af_checBesFichero.isSelected()) {
				a.createNewFile();
				congrat.setContentText(a.getName() + " es un fichero y se ha creado");
				congrat.showAndWait();
			} else { if(af_checBesCarpeta.isSelected()){
				a.mkdir();
				congrat.setContentText(a.getName() + " es un directorio y se ha creado");
				congrat.showAndWait();	
			}else{alert.setContentText("Seleccione el checkbox correspondiente");
			alert.showAndWait();
			}}
		}
		
		}
		else {
      		 alert.setContentText("Escribre el nombre del fichero a crear");
      		 alert.showAndWait();
      	 }
	}

	    @FXML
	    void afOnbutEliminar(ActionEvent event) {
	    	Alert alert=new Alert(AlertType.WARNING);
			Alert congrat=new Alert(AlertType.CONFIRMATION);
	    	if(!rutaafcwd.get().isBlank()){
	    	String rut = rutaaf.get().concat(rutaafcwd.get());
			File a = new File(rut);
			if (a.exists()) {

				if (af_checBesFichero.isSelected()) {
					congrat.setContentText(a.getName() + " es un fichero y se ha borrado");
					a.delete();	
					congrat.showAndWait();
				} else {if(af_checBesCarpeta.isSelected()){
					congrat.setContentText(a.getName() + " es un directorio y se ha borrado");
					a.delete();
					congrat.showAndWait();
				}}		
			}
              else {
            	  alert.setContentText("no existe");}
				  alert.showAndWait();
	    	}
			else {
	       		 alert.setContentText("Escribre el nombre del fichero a modificar");
	       		 alert.showAndWait();
	       	 }
	    }

	    @FXML
	    void afOnbutFicheroDirectorio(ActionEvent event) {
	    	if(!rutaaf.get().isBlank()){
	    	String rut = rutaaf.get();
	    	File folder = new File(rut);
			File[] listOfFiles = folder.listFiles();
			String concat="";
			int i=0;
			for (File file : listOfFiles) {
				String e=file.getName();
			    if (file.isFile()) {
			        System.out.println(file.getName());
			        
			    }
			    else if (file.isDirectory()) {
			        System.out.println(file.getName());
			    }
			    concat=concat+e+"\n";
		}
			list.set(concat);
			af_lvFicheroDirectorio.setEditable(false);;
	    }else {
      		 Alert alert=new Alert(AlertType.WARNING);
      		 alert.setContentText("Escribre la ruta del fichero a visualizar");
      		 alert.showAndWait();
      	 }}

	    @FXML
	    void afOnbutModFichero(ActionEvent event) throws IOException {
	    	if(!rutaafcwd.get().isBlank()){
	    	String rut = rutaaf.get().concat(rutaafcwd.get());
	    	FileWriter fw=new FileWriter(rut);
	    	
	    	fw.write(af_contenido.get());
	    	fw.close();
	    	}
	    	else {
       		 Alert alert=new Alert(AlertType.WARNING);
       		 alert.setContentText("Escribre el nombre del fichero a modificar");
       		 alert.showAndWait();
       	 }
	    }

	    @FXML
	    void afOnbutMover(ActionEvent event) throws IOException {
	    	Alert congrat=new Alert(AlertType.CONFIRMATION);
			Alert alert=new Alert(AlertType.WARNING);
	    	if(!rutaafcwd.get().isBlank()){
	    	TextInputDialog dialog = new TextInputDialog("ruta");
	    	dialog.setTitle("Text Input Dialog");
	    	dialog.setHeaderText("Escribe la ruta");
	    	dialog.setContentText("ruta ejemplo C:\\Users\\david\\PruebaArchivos\\ewgqwg.txt");

	    	// Traditional way to get the response value.
	    	Optional<String> result = dialog.showAndWait();
	    	if (result.isPresent()){
	    		congrat.setContentText("Nombre del directorio: " + result.get());
	    	} else {alert.setContentText("escriba el nombre de la ruta con nombre del fichero");}
	    	
	    	
	    	String rut = rutaaf.get().concat(rutaafcwd.get());
	    	String rutb = result.get();
			File a = new File(rut);
			File b= new File(rutb);
			
			if (a.exists()) {
				System.out.println(a.getName() + " viejo");
				if (af_checBesFichero.isSelected()& !af_checBesCarpeta.isSelected()) {
					a.renameTo(b);
				} else {if(af_checBesCarpeta.isSelected() & !af_checBesFichero.isSelected()){
					a.renameTo(b);
				}
				else {alert.setContentText("Seleccione un checkbox");}
				}
				congrat.setContentText(b.getName() + " nuevo");
				congrat.showAndWait();
			}
			
              else {
            	  alert.setContentText("no existe");
      			alert.showAndWait();
			}}
	    	else {
            		 alert.setContentText("Escribre el nombre");
            		 alert.showAndWait();
            	 }
	    	
	    }
	    @FXML
	    void afOnchecBesCarpeta(ActionEvent event) {
	    	af_checBesFichero.setDisable(true);
	    	if(!af_checBesCarpeta.isSelected()) {
	    		af_checBesFichero.setDisable(false);
	    	}
	    }

	    @FXML
	    void afOnchecBesFichero(ActionEvent event) {
	    	af_checBesCarpeta.setDisable(true);
	    	if(!af_checBesFichero.isSelected()) {
	    		af_checBesCarpeta.setDisable(false);
	    	}
	    }
	    //AX
	    
	    @FXML
	    void axOnbutAddEquipo(ActionEvent event) throws FileNotFoundException, JDOMException, IOException {
	    	
	    	if(!ax_fut.get().isBlank() &&
	    			!ax_FechIn.get().isBlank() &&
	    				!ax_FechFin.get().isBlank() &&
	    					!ax_nombEqui.get().isBlank()){
	    		
	    		
	    			SAXBuilder builder=new SAXBuilder();
					Document documentJDOM=builder.build(new FileInputStream(rutaax.get()));
					Element raiz=documentJDOM.getRootElement();
					List<Element> hijosRaiz=raiz.getChildren();
					
					for(Element hijoHijo:hijosRaiz) {
						
						String nombre= hijoHijo.getAttributeValue("nomEquipo");
						
						if(nombre!=null) {
							if(nombre.equals(ax_nombEqui.get())){
								
								Element tag=new Element("Futbolista");
								
								tag.setText(ax_fut.get());
								tag.setAttribute("fechaInicio",ax_FechIn.get());
								tag.setAttribute("fechaFin",ax_FechFin.get());
								
								Element wrap = (Element) hijoHijo.getChild("Contratos");
								wrap.addContent(tag);
								ax_contenido.set("Contrato añadido");
							}
						}else {
							ax_contenido.set("El nombre no concuerda con un equipo existente");}
					}
					XMLOutputter salida = new XMLOutputter(Format.getPrettyFormat());
					FileOutputStream file = new FileOutputStream(rutaax.get());
					salida.output(documentJDOM, file);
					
	    	} else {ax_contenido.set("Faltan valores");}
	    }

	    @FXML
	    void axOnbutEliminar(ActionEvent event) throws FileNotFoundException, JDOMException, IOException {
	    	try {
	    	String nombreEliminar=ax_nombEqui.get();
	    	SAXBuilder builder = new SAXBuilder();
	    	Document documentJDOM = builder.build(new FileInputStream(rutaax.get()));
	    	Element raiz = documentJDOM.getRootElement();
	    	List<Element> hijosRaiz = raiz.getChildren();
	    	Element equipoEliminar=null;

			for(Element hijo:hijosRaiz) {
				String nombreHijo=hijo.getAttributeValue("nomEquipo");
				if(nombreHijo!=null) {
					if(nombreHijo.equals(nombreEliminar))
						equipoEliminar=hijo;
				}
			}
			if(equipoEliminar!=null)
				ax_contenido.set("Equipo eliminado");
			
			hijosRaiz.remove(equipoEliminar); 
			XMLOutputter salida=new XMLOutputter(Format.getPrettyFormat());
			FileOutputStream file=new FileOutputStream(rutaax.get());
			salida.output(documentJDOM, file);	
	    	}catch (Exception e) {
	    		ax_contenido.set("El nombre del equipo no existe");
			}
	    }

	    @FXML
	    void axOnbutModCop(ActionEvent event) throws FileNotFoundException, JDOMException, IOException {

	    	if(!ax_nombEqui.get().isBlank()) {
		    	String numCopas=ax_Copas.getValue();
		    	String nomEquipo=ax_nombEqui.get();
		    	
		    	
			    	SAXBuilder builder=new SAXBuilder();
					Document documentJDOM=builder.build(new FileInputStream(rutaax.get()));
					Element raiz=documentJDOM.getRootElement();
					List<Element>hijosRaiz=raiz.getChildren();
					
					for(Element hijoHijo:hijosRaiz) { 
						
						String nombre=hijoHijo.getAttributeValue("nomEquipo");
						if(nombre!=null) {
							if(nombre.equals(nomEquipo)) {
								hijoHijo.setAttribute("copasGanadas",numCopas);
						    	
							}
						}
					}
					XMLOutputter salida= new XMLOutputter(Format.getPrettyFormat());
					FileOutputStream file = new FileOutputStream(rutaax.get());
					salida.output(documentJDOM, file);
					ax_contenido.set("Las copas de "+ nomEquipo+" ahora son "+numCopas);
	    	} else {ax_contenido.set("No se han modificado las copas");}
	    }

	    @FXML
	    void axOnbutVerdatos(ActionEvent event) throws FileNotFoundException, JDOMException, IOException {
	    	SAXBuilder builder = new SAXBuilder();
	    	if(!rutaax.get().isBlank()) {
	    	Document documentJDOM = builder.build(new FileInputStream(rutaax.get()));
	    	Element raiz = documentJDOM.getRootElement();
	    	List<Element> hijosRaiz = raiz.getChildren();
	    	String contenido="";
			for(Element hijo:hijosRaiz) {
				String nombreEtiqueta=hijo.getName();
				String nomEquipo=hijo.getAttributeValue("nomEquipo");
				String copasGanadas=hijo.getAttributeValue("copasGanadas");
				
				contenido+="\n "+nombreEtiqueta;
				contenido+="-Nombre del equipo: "+nomEquipo+" -Copas ganadas "+copasGanadas;
			
				List<Element>hijosHijo=hijo.getChildren();
				
				for(Element hijoSegundo:hijosHijo) {
					
					String nombreEtiquetaSegundo=hijoSegundo.getName();
					String textoSegundo=hijoSegundo.getText();
					
					if(nombreEtiquetaSegundo=="Contratos") {
						
						contenido+="\n      "+nombreEtiquetaSegundo;
									 
						List<Element>hijosHijoSegundo=hijoSegundo.getChildren();
						
						
						for(Element hijoTercero:hijosHijoSegundo) {
							
							String nombreEtiquetaTercero=hijoTercero.getName();
							String textoTercero=hijoTercero.getText();
							String fechaInicio=hijoTercero.getAttributeValue("fechaInicio");
							String fechaFin=hijoTercero.getAttributeValue("fechaFin");
							
							contenido+="\n         "+nombreEtiquetaTercero;
							contenido+="\n            -"+textoTercero	
									+" -Fecha inicio: "+fechaInicio+" -Fecha fin: "+fechaFin;	
						}
					}else {
						contenido+="\n      "+nombreEtiquetaSegundo+"\n         -"+textoSegundo;
					}
				}
				contenido+="\n__________________________________________________________________";
			}
	    	
	    	ax_contenido.set(contenido);
	    }else{Alert alert=new Alert(AlertType.WARNING);
	    	alert.setContentText("Escribre la ruta del fichero");
	    	alert.showAndWait();}}

	    @FXML
	    void axOnbutWritOtroFich(ActionEvent event) {
	    	Alert alert=new Alert(AlertType.WARNING);
	    	if(!ax_otroFile.get().isBlank()) {
		    	File currentFile=new File(rutaax.get());
		    	
		    	if(currentFile.isFile()) {
			    	try {
			        	SAXBuilder builder=new SAXBuilder();
						Document documentJDOM=builder.build(new FileInputStream(rutaax.get()));						
						XMLOutputter salida=new XMLOutputter(Format.getPrettyFormat());
						FileOutputStream file=new FileOutputStream(currentFile.getParent()+File.separator+
								ax_otroFile.get()+".xml");
						salida.output(documentJDOM, file);
						ax_contenido.set("xml escrito en "+ax_otroFile.get()+".xml");			
		
					} catch (Exception e) {
						e.printStackTrace();
					}
		    	}else {alert.setContentText("No es un fichero");
	      		 alert.showAndWait();}
	    	} else {alert.setContentText("Escribre la ruta del fichero a donde quieras escribirlo");
      		 alert.showAndWait();}
	    }
	    
		public void initialize(URL location, ResourceBundle resources) {
			aa_id.setDisable(true);
			//bindeos aceso a ficheros
			
			rutaaf.bind(af_ruta.textProperty());
			rutaafcwd.bind(af_carpetaFichero.textProperty());
			af_lvFicheroDirectorio.textProperty().bind(list);
			af_txareaContfichero.textProperty().bindBidirectional(af_contenido);
			//bindeos aceso aleatorios
			rutaaa.bind(aa_ruta.textProperty());
			rutaaacwd.bind(aa_nombreFichero.textProperty());
			aa_txareaContFichero.textProperty().bindBidirectional(aa_contenido);
			aa_visuaequipo.bind(aa_txfEquipo.textProperty());
			//bindeos aceso x
			rutaax.bind(ax_ruta.textProperty());
			ax_otroFile.bind(ax_txfWritOtroFich.textProperty());
			ax_txareaVerdatos.textProperty().bind(ax_contenido);
			ax_nombEqui.bind(ax_txfEquipo.textProperty());
			ax_fut.bind(ax_txfFutbolista.textProperty());
			ax_FechIn.bind(ax_txfFechaIni.textProperty());
			ax_FechFin.bind(ax_txfFechaFin.textProperty());
			ax_Copas.bind( ax_txfModCop.textProperty());}
		public VistaController() throws IOException{
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/View.fxml"));
			loader.setController(this);
			loader.load();
		}
		public AnchorPane getView() {
			return view;
		}
	}