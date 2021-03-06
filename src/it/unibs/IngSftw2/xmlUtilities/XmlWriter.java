package it.unibs.IngSftw2.xmlUtilities;

import  it.unibs.IngSftw2.mainClasses.ParametriScambi;
import it.unibs.IngSftw2.mainClasses.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.ArrayList;
/**
 * classe per la gestione del salvataggio su file xml del sistema
 * @author  Enrico Zambello, Jacopo Tedeschi
 */
public class XmlWriter {
    /**
     * metodo per salvare i dati su file xml all'inteno del package
     * @param s sistema di cui si salavano i dati
     */
    public static void salvaSistema(Sistema s, String filename){
        try{
            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();
            //sistema
            Element sistema= document.createElement("insiemeGerarchie");
            document.appendChild(sistema);
            int countGer=0;
            for(Gerarchia g: s.getListaGerarchie()){
                //gerarchia
                Element gerarchia =document.createElement("gerarchia");
                // Attr numberGerarchia= document.createAttribute("id");
                //numberGerarchia.setValue(""+countGer);
                sistema.appendChild(gerarchia);

                ArrayList <Categoria> allCat=new ArrayList<>();
                int countCat=0;
                for(Categoria x:g.getRamo().keySet()){
                    //categoria
                    Element categoria=document.createElement("categoria");
                    // Attr numberCategoria=document.createAttribute("id");
                    //numberCategoria.setValue(""+countCat);
                    gerarchia.appendChild(categoria);
                    //nome categoria
                    Element nomeCategoria=document.createElement("nomeCategoria");
                    categoria.appendChild(nomeCategoria);
                    nomeCategoria.appendChild(document.createTextNode(x.getNome()));
                    //descrizione categoria
                    Element descrizione =document.createElement("descrizione");
                    categoria.appendChild(descrizione);
                    descrizione.appendChild(document.createTextNode(x.getDescrizione()));
                    int countCampo=0;
                    //campi nativi
                    Element campiNativi=document.createElement("campiNativi");
                    categoria.appendChild(campiNativi);
                    for(CampoNativo c:x.getCampiNativi()){
                        //campoNativo
                        Element campoNativo=document.createElement("campoNativo");
                        //Attr numberCampo=document.createAttribute("id");
                        //numberCategoria.setValue(""+countCampo);
                        campiNativi.appendChild(campoNativo);

                        //nome campo nativo
                        Element nomeCampo=document.createElement("nomeCampo");
                        campoNativo.appendChild(nomeCampo);
                        nomeCampo.appendChild(document.createTextNode(c.getNomeCampo()));

                        //obbligo descrzione campo
                        Element obbligoCampo=document.createElement("obbligoCampo");
                        campoNativo.appendChild(obbligoCampo);
                        if(c.isObbligatoria()){
                            obbligoCampo.appendChild(document.createTextNode("true"));
                        }
                        else {
                            obbligoCampo.appendChild(document.createTextNode("false"));
                        }
                        countCampo++;

                    }
                    //padre categoria
                    Element padre=document.createElement("categoriaPadre");
                    categoria.appendChild(padre);
                    padre.appendChild(document.createTextNode(g.getRamo().get(x).getNome()));
                    countCat++;

                }

                countGer++;

            }
            Transformer transformer2 = TransformerFactory.newInstance().newTransformer();
            Result output = new StreamResult(new File(filename));
            Source input = new DOMSource(document);
            transformer2.transform(input, output);

            //trasforma il DOM Object in un file xml da salvare in un percorso valido
            //TransformerFactory transformerFactory = TransformerFactory.newInstance();
            //Transformer transformer = transformerFactory.newTransformer();
            //DOMSource domSource = new DOMSource(document);
            //StreamResult streamResult = new StreamResult(new File(xmlFilePath)); //xmleFilePath percoso valido dove salvare nel pc

        }catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        }

    }

    public static void utentiWrite(DatiUtenti utenti, String filename) throws ParserConfigurationException {
        try{
            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();

            Element datiUtenti= document.createElement("datiUtenti");
            document.appendChild(datiUtenti);
            for(Utente x: utenti.getListaUtenti()){
                Element utente= document.createElement("utente");
                datiUtenti.appendChild(utente);

                //username
                Element username=document.createElement("username");
                username.appendChild(document.createTextNode(x.getUsername()));
                utente.appendChild(username);


                //password
                Element password= document.createElement("password");
                password.appendChild(document.createTextNode(x.getPassword()));
                utente.appendChild(password);

                //configuratore o fruitore
                Element tipoUtente= document.createElement("tipoUtente");
                if(x instanceof Configuratore){
                    tipoUtente.appendChild(document.createTextNode("configuratore"));
                }
                else{
                    tipoUtente.appendChild(document.createTextNode("fruitore"));
                }
                utente.appendChild(tipoUtente);

            }
            Transformer transformer2 = TransformerFactory.newInstance().newTransformer();
            Result output = new StreamResult(new File(filename));
            Source input = new DOMSource(document);
            transformer2.transform(input, output);
        }catch (ParserConfigurationException | TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

    /**
     * metodo per il salvataggio xml dei parametri
     * @param p parametri da salvare
     * @param filename nom file xml su cui salvare
     */
    public static void salvaParametri(ParametriScambi p, String filename){
        try{
            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();

            Element parametri=document.createElement("ParametriScambi");
            document.appendChild(parametri);

            Element piazza=document.createElement("piazza");
            piazza.appendChild(document.createTextNode(p.getPiazza()));
            parametri.appendChild(piazza);

            Element luoghi=document.createElement("luoghi");
            parametri.appendChild(luoghi);

            for(String l: p.getLuoghi()){
                Element luogo=document.createElement("luogo");
                luogo.appendChild(document.createTextNode(l));
                luoghi.appendChild(luogo);
            }

            Element giorni= document.createElement("giorni");
            parametri.appendChild(giorni);

            for(Giorno g:p.getGiorni()){
                Element giorno=document.createElement("giorno");
                giorno.appendChild(document.createTextNode(g.toString()));
                giorni.appendChild(giorno);
            }

            Element intervalli=document.createElement("intervalli");
            parametri.appendChild(intervalli);
            for(Intervallo x: p.getIntervalli()){
                Element intervallo=document.createElement("intervallo");
                intervalli.appendChild(intervallo);

                Element orarioIniziale=document.createElement("orarioIniziale");
                orarioIniziale.appendChild(document.createTextNode(x.getOre()[0].toStringOrario()));
                intervallo.appendChild(orarioIniziale);
                Element orarioFinale=document.createElement("orarioFinale");
                orarioFinale.appendChild(document.createTextNode(x.getOre()[1].toStringOrario()));
                intervallo.appendChild(orarioFinale);
            }



            Element scadenza=document.createElement("scadenza");
            scadenza.appendChild(document.createTextNode(Integer.toString(p.getScadenza())));
            parametri.appendChild(scadenza);

            Transformer transformer2 = TransformerFactory.newInstance().newTransformer();
            Result output = new StreamResult(new File(filename));
            Source input = new DOMSource(document);
            transformer2.transform(input, output);

        }catch(ParserConfigurationException | TransformerConfigurationException e){

        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }
}