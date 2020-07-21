// Copyright © 2012-2020 VLINGO LABS. All rights reserved.
//
// This Source Code Form is subject to the terms of the
// Mozilla Public License, v. 2.0. If a copy of the MPL
// was not distributed with this file, You can obtain
// one at https://mozilla.org/MPL/2.0/.
package io.vlingo.xoom.codegen;

import io.vlingo.xoom.codegen.template.TemplateFileMocker;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static io.vlingo.xoom.codegen.template.TemplateStandard.AGGREGATE;
import static io.vlingo.xoom.codegen.template.TemplateStandard.STATE;

public class ContentQueryTest {

    private static final String AUTHOR_STATE_CONTENT_TEXT =
            "package io.vlingo.xoomapp.model; \\n" +
            "import io.vlingo.symbio.store.object.StateObject; \\n" +
            "public class AuthorState { \\n" +
            "... \\n" +
            "}";

    private static final String BOOK_STATE_CONTENT_TEXT =
            "package io.vlingo.xoomapp.model; \\n" +
                    "import io.vlingo.symbio.store.object.StateObject; \\n" +
                    "public class BookState { \\n" +
                    "... \\n" +
                    "}";

    private static final String AGGREGATE_CONTENT_TEXT =
            "package io.vlingo.xoomapp.model; \\n" +
            "public class Author { \\n" +
            "... \\n" +
             "}";

    @Test
    public void testClassNameQuery() {
        final List<String> classNames =
                ContentQuery.findClassNames(STATE, contents());

        Assert.assertEquals(2, classNames.size());
        Assert.assertTrue(classNames.contains("AuthorState"));
        Assert.assertTrue(classNames.contains("BookState"));
    }

    @Test
    public void testQualifiedClassNameQuery() {
        final List<String> classNames =
                ContentQuery.findFullyQualifiedClassNames(STATE, contents());

        Assert.assertEquals(2, classNames.size());
        Assert.assertTrue(classNames.contains("io.vlingo.xoomapp.model.AuthorState"));
        Assert.assertTrue(classNames.contains("io.vlingo.xoomapp.model.BookState"));
    }

    private List<Content> contents() {
        return Arrays.asList(
            Content.with(STATE, TemplateFileMocker.mock("/Projects/", "AuthorState.java"), AUTHOR_STATE_CONTENT_TEXT),
            Content.with(STATE, TemplateFileMocker.mock("/Projects/", "BookState.java"), BOOK_STATE_CONTENT_TEXT),
            Content.with(AGGREGATE, TemplateFileMocker.mock("/Projects/", "Author.java"), AGGREGATE_CONTENT_TEXT)
        );
    }

}
