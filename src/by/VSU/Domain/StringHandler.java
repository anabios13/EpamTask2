package by.VSU.Domain;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Character.isDigit;
import static java.lang.Character.isLetter;

public class StringHandler {
    public String AddWordIntoTheText(String line, String word, int length) {
        int numberOfSentence = 0;//номер предложения в котором меняем слово
        String bufferForCharArray = "";
        Pattern pattern = Pattern.compile("^[а-яА-Яa-zA-Z0-9_]+$");
        Matcher matcher;
        Pattern patternForPnoneNum = Pattern.compile("\\+\\d{3}\\(\\d{2}\\)\\d{3}-\\d{2}-\\d{2}.*");
        Matcher matcherForPnoneNum;
        Pattern patternForMail = Pattern.compile("\\w+@\\w+.(com|ru)\\.*");
        Matcher matcherForMail;
        String arrayOfSentences[] = line.split("(?<=[.!?])(\\s|\\n|\\f)");//разделение строки на массив предложений
        String arrayOfWords[] = arrayOfSentences[numberOfSentence].split(" ");//разделение предложения на слова
        for (int i = 0; i < arrayOfWords.length; i++) {
            String bufferForRegEx="";
            int bufferForLength=arrayOfWords[i].length();
            bufferForCharArray = "";
            matcher = pattern.matcher(arrayOfWords[i]);
            matcherForMail = patternForMail.matcher(arrayOfWords[i]);
            matcherForPnoneNum = patternForPnoneNum.matcher(arrayOfWords[i]);
            boolean flag = matcher.find();
            boolean flagForPhoneNum = matcherForPnoneNum.find();
            boolean flagForMail = matcherForMail.find();
            if (flagForMail || flagForPhoneNum || flag) {
                if ((i==arrayOfWords.length-1)&&(flagForMail || flagForPhoneNum)) {
                    char bufferForLetters[] = arrayOfWords[i].toCharArray();//разложение по символам
                    int j=bufferForLetters.length-1;
                        boolean flagForBufferForLettersLett=false;
                        boolean flagForBufferForLettersDig=false;
                        while (flagForBufferForLettersLett==false&&flagForBufferForLettersDig==false&&j>=0){
                            j--;
                            flagForBufferForLettersLett=isLetter(bufferForLetters[j]);
                            flagForBufferForLettersDig=isDigit(bufferForLetters[j]);
                            bufferForLength--;
                    }
                }
                if (bufferForLength== length) {
                    for (int j=bufferForLength;j<arrayOfWords[i].length();j++) {
                        bufferForRegEx +=arrayOfWords[i].toCharArray()[j];
                    }
                    arrayOfWords[i] =word +bufferForRegEx;
                }
                else if (arrayOfWords[i].length() == length) {
                    arrayOfWords[i] = word;
                }
            } else {
                char bufferForLetters[] = arrayOfWords[i].toCharArray();//разделение слов на отдельные символы
                int indexOfRightSymbol = 0;
                int checker = 0;
                int bufferForChecker = 0;
                for (int j = 0; j < bufferForLetters.length; j++) {
                    if (isLetter(bufferForLetters[j])) {//посимвольная проверка
                        checker++;
                        if (bufferForChecker <= checker) {
                            bufferForChecker = checker;
                            if (checker == length)
                                indexOfRightSymbol = j;
                        }
                    } else {
                        checker = 0;
                    }
                }
                if (bufferForChecker == length) {
                    for (int j = 0; j < indexOfRightSymbol - (length - 1); j++)
                        bufferForCharArray += bufferForLetters[j];
                    bufferForCharArray += word;
                    for (int j = length; j < bufferForLetters.length; j++)
                        bufferForCharArray += bufferForLetters[j];
                    arrayOfWords[i] = bufferForCharArray;
                }
            }
            arrayOfWords[i]+=" ";
        }
        if(!arrayOfWords[arrayOfWords.length-1].contains("\n"))
            arrayOfWords[arrayOfWords.length-1]+="\n";
        for(int j=0;j<arrayOfSentences.length;j++){
            if(!arrayOfSentences[j].contains("\n"))
                arrayOfSentences[j]+="\n";
        }

        arrayOfSentences[numberOfSentence] = "";
        for (int j = 0; j < arrayOfWords.length; j++)
            arrayOfSentences[numberOfSentence] += arrayOfWords[j];
        line = "";
        for (int j = 0; j < arrayOfSentences.length; j++)
            line += arrayOfSentences[j];
        System.out.println(line);
        return line;
    }
}
