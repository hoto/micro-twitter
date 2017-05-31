package com.microtwitter.users

import com.microtwitter.time.Clock
import spock.lang.Specification

class UserRepositoryTest extends Specification {
    private UserRepository userRepository

    def setup() {
        userRepository = new UserRepository(Mock(Clock))
    }

    def 'should return user with no name when user name is empty'() {
        when:
        User user = userRepository.getOrCreate('')

        then:
        user.name == ''
    }

    def 'should return user with a name when user name is not empty'() {
        when:
        User user = userRepository.getOrCreate('Alice')

        then:
        user.name == 'Alice'
    }

    def 'should return same user when user name is the same'() {
        when:
        User user1 = userRepository.getOrCreate('Alice')
        User user2 = userRepository.getOrCreate('Alice')

        then:
        user1 == user2
    }

    def 'should return two different users when user names are different'() {
        when:
        User user1 = userRepository.getOrCreate('Alice')
        User user2 = userRepository.getOrCreate('Bob')

        then:
        user1 != user2
    }
}
