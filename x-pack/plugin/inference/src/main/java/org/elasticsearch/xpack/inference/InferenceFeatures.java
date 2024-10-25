/*
 * Copyright Elasticsearch B.V. and/or licensed to Elasticsearch B.V. under one
 * or more contributor license agreements. Licensed under the Elastic License
 * 2.0; you may not use this file except in compliance with the Elastic License
 * 2.0.
 */

package org.elasticsearch.xpack.inference;

import org.elasticsearch.features.FeatureSpecification;
import org.elasticsearch.features.NodeFeature;
import org.elasticsearch.xpack.inference.mapper.SemanticTextFieldMapper;
import org.elasticsearch.xpack.inference.rank.random.RandomRankRetrieverBuilder;
import org.elasticsearch.xpack.inference.rank.textsimilarity.TextSimilarityRankRetrieverBuilder;

import java.util.HashSet;
import java.util.Set;

/**
 * Provides inference features.
 */
public class InferenceFeatures implements FeatureSpecification {

    @Override
    public Set<NodeFeature> getFeatures() {
        var features = new HashSet<NodeFeature>();
        features.add(TextSimilarityRankRetrieverBuilder.TEXT_SIMILARITY_RERANKER_RETRIEVER_SUPPORTED);
        features.add(RandomRankRetrieverBuilder.RANDOM_RERANKER_RETRIEVER_SUPPORTED);
        features.add(SemanticTextFieldMapper.SEMANTIC_TEXT_SEARCH_INFERENCE_ID);
        features.add(TextSimilarityRankRetrieverBuilder.TEXT_SIMILARITY_RERANKER_COMPOSITION_SUPPORTED);
        if (DefaultElserFeatureFlag.isEnabled()) {
            features.add(SemanticTextFieldMapper.SEMANTIC_TEXT_DEFAULT_ELSER_2);
        }
        return Set.copyOf(features);
    }

    @Override
    public Set<NodeFeature> getTestFeatures() {
        return Set.of(SemanticTextFieldMapper.SEMANTIC_TEXT_IN_OBJECT_FIELD_FIX);
    }
}
