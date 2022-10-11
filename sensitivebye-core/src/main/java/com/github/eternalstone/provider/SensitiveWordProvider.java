package com.github.eternalstone.provider;

import com.github.eternalstone.attachment.wordsource.ISensitiveWordSource;
import com.github.eternalstone.attachment.wordsource.SensitiveWordSourceFromResource;
import com.hankcs.algorithm.AhoCorasickDoubleArrayTrie;

import java.util.ArrayList;
import java.util.List;

/**
 * 敏感词条
 *
 * @author Justzone on 2022/8/30 14:56
 */
public class SensitiveWordProvider implements ISensitiveWordProvider {

    private ISensitiveWordSource source;

    public SensitiveWordProvider() {
        //默认从resource目录下读取sensitive.txt文件
        this.source = new SensitiveWordSourceFromResource();
    }

    public SensitiveWordProvider(ISensitiveWordSource sensitiveWordSource) {
        this.source = sensitiveWordSource;
    }

    /**
     * 刷新词库
     */
    public void reload() {
        SensitiveDictionary.reload(() -> source.loadSource());
    }


    @Override
    public String handle(String word, String symbol) {
        List<AhoCorasickDoubleArrayTrie.Hit<String>> hits = SensitiveDictionary.handle(() -> source.loadSource(), word);
        if (hits.size() == 0) {
            return word;
        }
        StringBuffer buffer = new StringBuffer(word);
        for (AhoCorasickDoubleArrayTrie.Hit<String> hit : hits) {
            buffer.replace(hit.begin, hit.end, symbol);
        }
        return buffer.toString();
    }

    @Override
    public List<String> contain(String word) {
        List<String> list = new ArrayList<>();
        List<AhoCorasickDoubleArrayTrie.Hit<String>> hits = SensitiveDictionary.handle(() -> source.loadSource(), word);
        for (AhoCorasickDoubleArrayTrie.Hit<String> hit : hits) {
            String sensitive = hit.value.trim();
            if (!list.contains(sensitive)) {
                list.add(sensitive);
            }
        }
        return list;
    }

}
