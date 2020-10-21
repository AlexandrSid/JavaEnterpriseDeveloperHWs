package com.alexsid.lesson6;

import java.util.Random;

/***
 * 2. Создать генератор текстовых файлов, работающий по следующим правилам:
 *
 * • Предложение состоит из 1<=n1<=15 слов. В предложении после произвольных слов могут находиться запятые.
 *
 * • Слово состоит из 1<=n2<=15 латинских букв
 *
 * • Слова разделены одним пробелом
 *
 * • Предложение начинается с заглавной буквы
 *
 * • Предложение заканчивается (.|!|?)+" "
 *
 * • Текст состоит из абзацев. в одном абзаце 1<=n3<=20 предложений. В конце абзаца стоит разрыв строки и перенос каретки.
 */

public final class TextGenerator {
    private int n1, n2, n3;
    private int commaFactor; // 1÷commaFactor - вероятность установки запятой после произвольного слова
    private WordProvider wordProvider;


    Random random = new Random();

    public String generateParagraph() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i <= random.nextInt(n3) + 1; i++) {
            builder.append(generateSentence());
        }
        builder.append("\n\r");
        return builder.toString();
    }

    public String generateSentence() {
        char[] endSymbols = new char[]{'.', '!', '?'};
        StringBuilder builder = new StringBuilder();

        String word = wordProvider.getWord();//чтобы сделать заглавным первый символ предложения
        builder.append(word.substring(0, 1).toUpperCase()).append(word.substring(1));//

        for (int i = 0; i <= random.nextInt(n1 - 1) + 1; i++) {
            if (random.nextInt(commaFactor) == 0)
                builder.append(',');//первое слово уже есть, а если второго не будет, то сюда не придём
            builder.append(" ").append(wordProvider.getWord());
        }

        builder.append(endSymbols[random.nextInt(3)]).append(" ");

        return builder.toString();
    }

    private final class InnerWordProviderImplementation implements WordProvider {
        private int alphStart = 'a';
        private int alphEnd = 'z';

        @Override
        public String getWord() {
            StringBuilder result = new StringBuilder();
            Random random = new Random();
            for (int i = 0; i <= random.nextInt(n2) + 1; i++) {
                result.append((char) (random.nextInt(alphEnd - alphStart) + alphStart));
            }
            return result.toString();//не стал возвращать Builder, так как в интерфейсе логичнее чтоб был String
        }
    }

    public TextGenerator(int n1, int n2, int n3, int commaFactor) {
        this.n1 = n1;
        this.n2 = n2;
        this.n3 = n3;
        this.commaFactor = commaFactor;
        this.wordProvider = new InnerWordProviderImplementation();
    }

    public TextGenerator(WordProvider wordProvider) {
        this.n1 = 15;
        this.n2 = 15;
        this.n3 = 20;
        this.commaFactor = 8;
        this.wordProvider = wordProvider;
    }
}
