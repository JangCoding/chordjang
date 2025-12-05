package com.example.chordjang.chord;

import org.springframework.data.jpa.domain.Specification;

public class ChordSpecs {

    public static Specification<Chord> equalId(Long id) {
        return (root, query, cb) ->
                id == null ? null : cb.equal(root.get("id"), id);
    }

    public static Specification<Chord> equalType(InstrumentType type) {
        return (root, query, cb) ->
                type == null ? null : cb.equal( root.get("type"), type);
    }

    public static Specification<Chord> equalRootNote(RootNote rootNote) {
        return (root, query, cb) ->
                rootNote == null ? null : cb.equal(root.get("rootNote"), rootNote);
    }

    public static Specification<Chord> equalQuality(Quality quality) {
        return (root, query, cb) ->
                quality == null ? null : cb.equal(root.get("quality"), quality);
    }
}