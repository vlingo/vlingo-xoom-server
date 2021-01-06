// Copyright © 2012-2020 VLINGO LABS. All rights reserved.
//
// This Source Code Form is subject to the terms of the
// Mozilla Public License, v. 2.0. If a copy of the MPL
// was not distributed with this file, You can obtain
// one at https://mozilla.org/MPL/2.0/.

package io.vlingo.xoom.exchange;

public class ExchangeParameter {

    public final String key;
    public final String value;

    public ExchangeParameter(final String key, final String value) {
        this.key = key;
        this.value = value;
    }

    public boolean hasKey(final String key) {
        return this.key.equals(key);
    }
}
