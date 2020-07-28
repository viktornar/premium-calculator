package com.github.viktornar.utils;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MathUtilTest {
    @Test
    void shouldCorrectlyRound() {
        Assert.assertEquals(2.28, MathUtil.round(2.28000002, 2), 0.00);
        Assert.assertEquals(2.29, MathUtil.round(2.2850000, 2), 0.00);
        Assert.assertEquals(2.28, MathUtil.round(2.2840000, 2), 0.00);
    }
}