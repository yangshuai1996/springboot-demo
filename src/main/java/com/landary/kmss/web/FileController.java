package com.landary.kmss.web;

import com.landary.kmss.entity.Control;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

/**
 * @Author 帅
 * @Date 2021/6/12 11:07
 * @Description 上传文件Controller
 **/
@RestController
public class FileController {
    //文件上传路径
    public static final String FILE_PATH = "D:/upload";


    @PostMapping("/uploadFile")
    public String uploadFile(MultipartFile[] file, Control control){
        if (file == null || file.length == 0)
            return "fail";
        //创建文件夹
        try {
                /*DateFormat format = new SimpleDateFormat("yyyy/MM/dd");
                String formatDate = format.format(new Date());
                String path = FILE_PATH + formatDate;
                File directory = new File(path);
                if(!directory.exists()){//不存在就创建
                    directory.mkdirs();
                }
                //获取当前文件夹的最大数
                String[] list = directory.list();
                int temp = 1;
                if(list != null && list.length > 0){
                        temp = Integer.parseInt(list[list.length - 1]) + 1;
                }
                String tempPath = String.valueOf(temp);
                while (tempPath.length() != 3){
                    tempPath = "0" +tempPath;
                }
                //创建文件夹
                String suffixPath = path +"/" +tempPath;
                directory = new File(suffixPath);
                directory.mkdir();*/
                //复制文件到指定文件夹下面
                String realPath = null;
                //原文件名
                String originalFilename = null;
                for (int i = 0; i < file.length; i++) {
                    originalFilename= file[i].getOriginalFilename();
                    if("".equals(originalFilename))
                        continue;
                    realPath = FILE_PATH + "/" + originalFilename;
                    file[i].transferTo(new File(realPath));
                }
            //读取模板
             Document doc = readXmlTemplate();
             //替换模板中的内容
            if(StringUtils.hasLength(control.getModel())){
                Node modelNode = doc.getElementsByTagName("Model").item(0);
                modelNode.setTextContent(control.getModel());
            }

            Node autoCalibration = doc.getElementsByTagName("AutoCalibration").item(0);
            autoCalibration.setTextContent(control.getAutoCalibration());

            Node scenario = doc.getElementsByTagName("Scenario").item(0);
            scenario.setTextContent(control.getScenario());

            Node gridSizeX = doc.getElementsByTagName("GridSizeX").item(0);
            gridSizeX.setTextContent(control.getGridSizeX());


            Node gridSizeY = doc.getElementsByTagName("GridSizeY").item(0);
            gridSizeY.setTextContent(control.getGridSizeY());

            Node xStartCoord = doc.getElementsByTagName("XStartCoord").item(0);
            xStartCoord.setTextContent(control.getxStartCoord());

            Node yStartCoord = doc.getElementsByTagName("YStartCoord").item(0);
            yStartCoord.setTextContent(control.getyStartCoord());

            Node xWindowSize = doc.getElementsByTagName("XWindowSize").item(0);
            xWindowSize.setTextContent(control.getxWindowSize());


            Node yWindowSize = doc.getElementsByTagName("YWindowSize").item(0);
            yWindowSize.setTextContent(control.getyWindowSize());

            Node xWindowStart = doc.getElementsByTagName("XWindowStart").item(0);
            xWindowStart.setTextContent(control.getxWindowStart());

            Node yWindowStart = doc.getElementsByTagName("YWindowStart").item(0);
            yWindowStart.setTextContent(control.getyWindowStart());

            Node xWindowEnd = doc.getElementsByTagName("XWindowEnd").item(0);
            xWindowEnd.setTextContent(control.getxWindowEnd());

            Node yWindowEnd = doc.getElementsByTagName("YWindowEnd").item(0);
            yWindowEnd.setTextContent(control.getyWindowEnd());

            Node rowScale = doc.getElementsByTagName("RowScale").item(0);
            rowScale.setTextContent(control.getRowScale());

            Node colScale = doc.getElementsByTagName("ColScale").item(0);
            colScale.setTextContent(control.getColScale());

            Node scenarioStartYear = doc.getElementsByTagName("ScenarioStartYear").item(0);
            scenarioStartYear.setTextContent(control.getScenarioStartYear());

            Node scenarioEndYear = doc.getElementsByTagName("ScenarioEndYear").item(0);
            scenarioEndYear.setTextContent(control.getScenarioEndYear());

            Node historicStartYear = doc.getElementsByTagName("HistoricStartYear").item(0);
            historicStartYear.setTextContent(control.getHistoricStartYear());

            Node historicEndYear = doc.getElementsByTagName("HistoricEndYear").item(0);
            historicEndYear.setTextContent(control.getHistoricEndYear());

            Node combineYears = doc.getElementsByTagName("CombineYears").item(0);
            combineYears.setTextContent(control.getCombineYears());

            Node basePixelSize = doc.getElementsByTagName("BasePixelSize").item(0);
            basePixelSize.setTextContent(control.getBasePixelSize());

            Node forTimberProductOutputYear = doc.getElementsByTagName("ForTimberProductOutputYear").item(0);
            forTimberProductOutputYear.setTextContent(control.getForTimberProductOutputYear());
            //将dom节点输出到文件中
            File controlFile = new File(FILE_PATH + "/control.xml");
            // 创建TransformerFactory对象
            TransformerFactory tff = TransformerFactory.newInstance();
            // 创建 Transformer对象
            Transformer tf = tff.newTransformer();

            // 输出内容是否使用换行
            tf.setOutputProperty(OutputKeys.INDENT, "yes");
            // 创建xml文件并写入内容
            tf.transform(new DOMSource(doc), new StreamResult(controlFile));
            } catch (Exception e) {
                 e.printStackTrace();
                 return "fail";
            }
        return "success";
    }

    private Document readXmlTemplate(){
        Document doc = null;
        try {
            //ClassPathResource classPathResource = new ClassPathResource("control.xml");
            //InputStream inputStream =classPathResource.getInputStream();
            // 获取文件
            //File file = classPathResource.getFile();
            File  file = new File("D:\\upload\\control.xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = null;
            builder = factory.newDocumentBuilder();
            doc = builder.parse(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
            return doc;
    }

    /**
     * @Description 创建xml文件
     * @Param
     **/
    /*private void createXmlFile(Control control) throws ParserConfigurationException {
        // 创建解析器工厂
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = factory.newDocumentBuilder();
        Document document = db.newDocument();
        // 不显示standalone="no"
        document.setXmlStandalone(true);
        Element gems = document.createElement("gems");

        Element modelConfig = document.createElement("ModelConfig");

        Element versionSW = document.createElement("VersionSW");
        modelConfig.appendChild(versionSW);
        Element model = document.createElement("Model");
        modelConfig.appendChild(model);
        Element autoCalibration = document.createElement("AutoCalibration");
        modelConfig.appendChild(autoCalibration);
        Element scenario = document.createElement("Scenario");
        modelConfig.appendChild(scenario);
        Element gridSizeX = document.createElement("GridSizeX");
        modelConfig.appendChild(gridSizeX);
        Element gridSizeY = document.createElement("GridSizeY");
        modelConfig.appendChild(gridSizeY);
        Element xStartCoord = document.createElement("XStartCoord");
        modelConfig.appendChild(xStartCoord);
        Element yStartCoord = document.createElement("YStartCoord");
        modelConfig.appendChild(yStartCoord);
        Element xWindowSize = document.createElement("XWindowSize");
        modelConfig.appendChild(xWindowSize);
        Element yWindowSize = document.createElement("YWindowSize");
        modelConfig.appendChild(yWindowSize);
        Element xWindowStart = document.createElement("XWindowStart");
        modelConfig.appendChild(xWindowStart);
        Element yWindowStart = document.createElement("YWindowStart");
        modelConfig.appendChild(yWindowStart);
        Element xWindowEnd = document.createElement("XWindowEnd");
        modelConfig.appendChild(xWindowEnd);
        Element yWindowEnd = document.createElement("YWindowEnd");
        modelConfig.appendChild(yWindowEnd);
        Element rowScale = document.createElement("RowScale");
        modelConfig.appendChild(rowScale);
        Element colScale = document.createElement("ColScale");
        modelConfig.appendChild(colScale);
        Element scenarioStartYear = document.createElement("ScenarioStartYear");
        modelConfig.appendChild(scenarioStartYear);
        Element scenarioEndYear = document.createElement("ScenarioEndYear");
        modelConfig.appendChild(scenarioEndYear);
        Element historicStartYear = document.createElement("HistoricStartYear");
        modelConfig.appendChild(historicStartYear);
        Element historicEndYear = document.createElement("HistoricEndYear");
        modelConfig.appendChild(historicEndYear);
        Element combineYears = document.createElement("CombineYears");
        modelConfig.appendChild(combineYears);
        Element basePixelSize = document.createElement("BasePixelSize");
        modelConfig.appendChild(basePixelSize);
        gems.appendChild(modelConfig);

        Element landCover = document.createElement("LandCover");
        Element landCover = document.createElement("LandCover");

        gems.appendChild(landCover);

    }*/
}
