package br.com.caelum.auction.domain;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

public class UserTest {

	@Test
	public void shouldEquals() {
		User user = new User("willian");
		assertThat(user,equalTo(new User("willian")));
	}

}
