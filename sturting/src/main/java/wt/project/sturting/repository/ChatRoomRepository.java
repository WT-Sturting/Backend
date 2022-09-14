package wt.project.sturting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wt.project.sturting.entity.ChatRoom;
import wt.project.sturting.entity.Study;

import java.util.Optional;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {
	Optional<ChatRoom> findByStudy(Study study);
}