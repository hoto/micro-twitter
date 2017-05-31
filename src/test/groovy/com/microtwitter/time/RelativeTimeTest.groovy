package com.microtwitter.time

import spock.lang.Specification
import spock.lang.Unroll

import static java.util.concurrent.TimeUnit.DAYS
import static java.util.concurrent.TimeUnit.HOURS
import static java.util.concurrent.TimeUnit.MINUTES
import static java.util.concurrent.TimeUnit.SECONDS

class RelativeTimeTest extends Specification {
    private RelativeTime relativeTime

    def setup() {
        relativeTime = new RelativeTime()
    }

    def 'should return empty string when duration is negative'() {
        expect:
        relativeTime.toElapsedDuration(-1) == ''
    }

    @Unroll
    def 'should return elapsed time when duration is positive'() {
        expect:
        relativeTime.toElapsedDuration(duration) == elapsed

        where:
        duration             || elapsed
        0                    || 'now'
        SECONDS.toMillis(1)  || '1 second ago'
        SECONDS.toMillis(2)  || '2 seconds ago'
        SECONDS.toMillis(59) || '59 seconds ago'
        SECONDS.toMillis(60) || '1 minute ago'
        MINUTES.toMillis(1)  || '1 minute ago'
        MINUTES.toMillis(2)  || '2 minutes ago'
        MINUTES.toMillis(59) || '59 minutes ago'
        MINUTES.toMillis(60) || '1 hour ago'
        HOURS.toMillis(2)    || '2 hours ago'
        HOURS.toMillis(23)   || '23 hours ago'
        HOURS.toMillis(24)   || '1 day ago'
        DAYS.toMillis(2)     || '2 days ago'
        DAYS.toMillis(1000)  || '1000 days ago'
    }
}
