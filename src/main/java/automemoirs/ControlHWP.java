package automemoirs;

import kr.dogfoot.hwplib.object.HWPFile;
import kr.dogfoot.hwplib.object.bodytext.Section;
import kr.dogfoot.hwplib.object.bodytext.paragraph.Paragraph;
import kr.dogfoot.hwplib.object.bodytext.paragraph.charshape.ParaCharShape;
import kr.dogfoot.hwplib.object.bodytext.paragraph.header.ParaHeader;
import kr.dogfoot.hwplib.object.docinfo.CharShape;
import kr.dogfoot.hwplib.object.docinfo.ParaShape;
import kr.dogfoot.hwplib.object.docinfo.borderfill.BorderType;
import kr.dogfoot.hwplib.object.docinfo.charshape.*;
import kr.dogfoot.hwplib.object.docinfo.parashape.Alignment;
import kr.dogfoot.hwplib.reader.HWPReader;
import kr.dogfoot.hwplib.tool.blankfilemaker.BlankFileMaker;
import kr.dogfoot.hwplib.tool.blankfilemaker.CharShapeAdder;
import kr.dogfoot.hwplib.tool.paragraphadder.docinfo.ParaShapeAdder;
import kr.dogfoot.hwplib.writer.HWPWriter;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.SplittableRandom;

public class ControlHWP {
    public static void createHWP(String path) throws Exception {
        HWPFile hwpFile = BlankFileMaker.make();
        if (hwpFile != null) {
            HWPWriter.toFile(hwpFile, path);
        }
    }

    public static void writeHWP(String path, String title, LocalDate date, String time, String place, String desc) throws Exception {
        String dateStr = date.getYear() + "년 " + date.getMonthValue() + "월 " + date.getDayOfMonth() + "일";
        HWPFile hwpFile = HWPReader.fromFile(path);
        String divisionLine = "________________________________________________________________________";
        if (hwpFile != null) {
            /**  문단과 내용매치표
             *   1. 타이틀
             *   2. 일시
             *   3. 시간
             *   4. 장소
             *   5. 나눔선
             *   6. 본문
             */
            int amountSections = 6;
            for (int i = 0; i < amountSections - 1; i++) {
                hwpFile.getBodyText().addNewSection();
            }

//            hwpFile.getBodyText().addNewSection();
//            hwpFile.getBodyText().addNewSection();
//            hwpFile.getBodyText().addNewSection();
//            hwpFile.getBodyText().addNewSection();

            ArrayList<Section> sections = new ArrayList<>();
            for (int i = 0; i < 6; i++) {
                sections.add(hwpFile.getBodyText().getSectionList().get(i));
            }
//
//            Section s1 = hwpFile.getBodyText().getSectionList().get(0);
//            Section s2 = hwpFile.getBodyText().getSectionList().get(1);
//            Section s3 = hwpFile.getBodyText().getSectionList().get(2);
//            Section s4 = hwpFile.getBodyText().getSectionList().get(3);
//            Section s5 = hwpFile.getBodyText().getSectionList().get(4);
//            Section s6 = hwpFile.getBodyText().getSectionList().get(5);
//            s1.addNewParagraph();
            for (int i = 1; i < amountSections; i++) {
                sections.get(i).addNewParagraph();
            }
//            sections.get(2).addNewParagraph();
//            sections.get(3).addNewParagraph();
//            sections.get(4).addNewParagraph();
//            sections.get(5).addNewParagraph();

            ArrayList<Paragraph> paragraphs = new ArrayList<>();
            for (int i = 0; i < amountSections; i++) {
                paragraphs.add(sections.get(i).getParagraph(0));
            }
//            Paragraph firstParagraph = sections.get(0).getParagraph(0);
//            Paragraph secondParagraph = sections.get(1).getParagraph(0);
//            Paragraph thirdParagraph = sections.get(2).getParagraph(0);
//            Paragraph fourthParagraph = sections.get(3).getParagraph(0);
//            Paragraph fifthParagraph = sections.get(4).getParagraph(0);
//            Paragraph sixthParagraph = sections.get(5).getParagraph(0);

            for (int i = 1; i < amountSections; i++) {
                paragraphs.get(i).createCharShape();
                paragraphs.get(i).createLineSeg();
                paragraphs.get(i).createText();
            }
//            paragraphs.get(1).createCharShape();
//            paragraphs.get(1).createLineSeg();
//            paragraphs.get(1).createText();
//
//            paragraphs.get(2).createCharShape();
//            paragraphs.get(2).createLineSeg();
//            paragraphs.get(2).createText();
//
//            paragraphs.get(3).createCharShape();
//            paragraphs.get(3).createLineSeg();
//            paragraphs.get(3).createText();
//
//            paragraphs.get(4).createCharShape();
//            paragraphs.get(4).createLineSeg();
//            paragraphs.get(4).createText();
//
//            paragraphs.get(5).createCharShape();
//            paragraphs.get(5).createLineSeg();
//            paragraphs.get(5).createText();

            ArrayList<String> inputStrings = new ArrayList<>(List.of(title, "일시: " + dateStr, "시간: " + time, "장소: " + place, divisionLine, desc));

            for(int i =0;i<amountSections;i++){
                paragraphs.get(i).getText().addString(inputStrings.get(i));
            }
//            paragraphs.get(0).getText().addString(title);
//            paragraphs.get(1).getText().addString("일시: " + dateStr);
//            paragraphs.get(2).getText().addString("시간: " + time);
//            paragraphs.get(3).getText().addString("장소: " + place);
//            paragraphs.get(4).getText().addString(divisionLine);
//            paragraphs.get(5).getText().addString(desc);


            paragraphs.get(0).getCharShape().addParaCharShape(0, createCharShapeTemplate(hwpFile, 20, true));
            paragraphs.get(1).getCharShape().addParaCharShape(0, createCharShapeTemplate(hwpFile, 11, false));
            paragraphs.get(2).getCharShape().addParaCharShape(0, createCharShapeTemplate(hwpFile, 11, false));
            paragraphs.get(3).getCharShape().addParaCharShape(0, createCharShapeTemplate(hwpFile, 11, false));
            paragraphs.get(4).getCharShape().addParaCharShape(0, createCharShapeTemplate(hwpFile, 11, true));
            paragraphs.get(5).getCharShape().addParaCharShape(0, createCharShapeTemplate(hwpFile, 11, false));
//            firstParagraph.getLineSeg().addNewLineSegItem();

//            CharShape cs = hwpFile.getDocInfo().addNewCharShape();
//            cs.setBaseSize(2000);
//            cs.getProperty().setBold(true);

//            ParaHeader ph = firstParagraph.getHeader();
//            ph.setLineAlignCount(1);

            int[] centerParagraphs = {0, 4};
            int[] leftParagraphs = {5};
            int[] rightParagraphs = {1,2,3};
            for (int i = 0; i < centerParagraphs.length; i++) {
                ParaHeader ph = paragraphs.get(centerParagraphs[i]).getHeader();
                ParaShape paraShape = hwpFile.getDocInfo().addNewParaShape();
                paraShape.getProperty1().setAlignment(Alignment.Center);
                paraShape.setLineSpace(160);
                int paraShapeId = hwpFile.getDocInfo().getParaShapeList().size() - 1;
                ph.setParaShapeId(paraShapeId);
            }
//            ParaShape paraShape1 = hwpFile.getDocInfo().addNewParaShape();
//            paraShape1.getProperty1().setAlignment(Alignment.Center);
//            paraShape1.setLineSpace(100);
//            int paraShapeIdCenter = hwpFile.getDocInfo().getParaShapeList().size() - 1;
//            paragraphs.get(0).getHeader().setParaShapeId(paraShapeIdCenter);
//            paragraphs.get(4).getHeader().setParaShapeId(paraShapeIdCenter);
            for (int i = 0; i < rightParagraphs.length; i++) {
                ParaHeader ph = paragraphs.get(rightParagraphs[i]).getHeader();
                ParaShape paraShape = hwpFile.getDocInfo().addNewParaShape();
                paraShape.getProperty1().setAlignment(Alignment.Right);
                paraShape.setLineSpace(160);
                int paraShapeId = hwpFile.getDocInfo().getParaShapeList().size() - 1;
                ph.setParaShapeId(paraShapeId);
            }
            for (int i = 0; i < leftParagraphs.length; i++) {
                ParaHeader ph = paragraphs.get(leftParagraphs[i]).getHeader();
                ParaShape paraShape = hwpFile.getDocInfo().addNewParaShape();
                paraShape.getProperty1().setAlignment(Alignment.Left);
                paraShape.setLineSpace(160);
                int paraShapeId = hwpFile.getDocInfo().getParaShapeList().size() - 1;
                ph.setParaShapeId(paraShapeId);
            }
//            ParaShape paraShape2 = hwpFile.getDocInfo().addNewParaShape();
//            paraShape2.getProperty1().setAlignment(Alignment.Right);
//            paraShape1.setLineSpace(100);
//            int paraShapeIdRight = hwpFile.getDocInfo().getParaShapeList().size() - 1;
//            paragraphs.get(1).getHeader().setParaShapeId(paraShapeIdRight);
//            paragraphs.get(2).getHeader().setParaShapeId(paraShapeIdRight);
//            paragraphs.get(3).getHeader().setParaShapeId(paraShapeIdRight);
//
//            ParaShape paraShape3 = hwpFile.getDocInfo().addNewParaShape();
//            paraShape3.getProperty1().setAlignment(Alignment.Left);
//            paraShape3.setLineSpace(100);
//            int paraShapeIdLeft = hwpFile.getDocInfo().getParaShapeList().size() - 1;
//            paragraphs.get(5).getHeader().setParaShapeId(paraShapeIdLeft);

            HWPWriter.toFile(hwpFile, path);
        }
    }

    public static void readPropHWP(String path) throws Exception {
        HWPFile hwpFile = HWPReader.fromFile(path);
        if (hwpFile != null) {
            Section s1 = hwpFile.getBodyText().getSectionList().get(0);
            Section s2 = hwpFile.getBodyText().getSectionList().get(1);
            Section s3 = hwpFile.getBodyText().getSectionList().get(2);
            Section s4 = hwpFile.getBodyText().getSectionList().get(3);
            Section s5 = hwpFile.getBodyText().getSectionList().get(4);
            Section s6 = hwpFile.getBodyText().getSectionList().get(5);

            Paragraph firstParagraph = s1.getParagraph(0);
            Paragraph secondParagraph = s2.getParagraph(0);
            Paragraph thirdParagraph = s3.getParagraph(0);
            Paragraph fourthParagraph = s4.getParagraph(0);
            Paragraph fifthParagraph = s5.getParagraph(0);
            Paragraph sixthParagraph = s6.getParagraph(0);

            CharShape charShape = new CharShape();
            charShape.getProperty().setBold(true);


            fifthParagraph.getCharShape().addParaCharShape(1, 123);
            System.out.println(firstParagraph.getCharShape().toString());
            System.out.println(secondParagraph.getCharShape().toString());
            System.out.println(thirdParagraph.getCharShape().toString());
            System.out.println(fourthParagraph.getCharShape().toString());
            System.out.println(fifthParagraph.getCharShape().toString());
            System.out.println(sixthParagraph.getCharShape().toString());
        }

    }

    private static int createCharShapeTemplate(HWPFile hwpFile, int fontSize, boolean bold) {
//        HWPFile hwpFile = new HWPFile();
        CharShape cs = hwpFile.getDocInfo().addNewCharShape();
        // 바탕 폰트를 위한 FaceName 객체를 링크한다. (link FaceName Object for 'Batang' font.)
        cs.getFaceNameIds().setForAll(0);

        cs.getRatios().setForAll((short) 100);
        cs.getCharSpaces().setForAll((byte) 0);
        cs.getRelativeSizes().setForAll((short) 100);
        cs.getCharOffsets().setForAll((byte) 0);
        cs.setBaseSize((int) (fontSize * 100.0f));

        cs.getProperty().setItalic(false);
        cs.getProperty().setBold(bold);
        cs.getProperty().setUnderLineSort(UnderLineSort.None);
        cs.getProperty().setOutterLineSort(OutterLineSort.None);
        cs.getProperty().setShadowSort(ShadowSort.None);
        cs.getProperty().setEmboss(false);
        cs.getProperty().setEngrave(false);
        cs.getProperty().setSuperScript(false);
        cs.getProperty().setSubScript(false);
        cs.getProperty().setStrikeLine(false);
        cs.getProperty().setEmphasisSort(EmphasisSort.None);
        cs.getProperty().setUsingSpaceAppropriateForFont(false);
        cs.getProperty().setStrikeLineShape(BorderType.None);
        cs.getProperty().setKerning(false);

        cs.setShadowGap1((byte) 0);
        cs.setShadowGap2((byte) 0);
        cs.getCharColor().setValue(0x00000000);
        cs.getUnderLineColor().setValue(0x00000000);
        cs.getShadeColor().setValue(-1);
        cs.getShadowColor().setValue(0x00b2b2b2);
        cs.setBorderFillId(0);

        return hwpFile.getDocInfo().getCharShapeList().size() - 1;
    }
}
