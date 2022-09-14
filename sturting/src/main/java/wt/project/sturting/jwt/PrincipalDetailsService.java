package wt.project.sturting.jwt;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import wt.project.sturting.entity.User;
import wt.project.sturting.entity.enums.UserMethod;
import wt.project.sturting.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService {
	private final UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User userEntity = userRepository.findByEmailAndMethod(username, UserMethod.NORMAL);

		if (userEntity == null) {
			throw new UsernameNotFoundException("UsernameNotFoundException");
		}

		return new CustomUserDetails(userEntity);
	}
}
