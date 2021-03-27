package az.ada.library.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import az.ada.library.Models.PickDropHistory;

public interface PickDropHistoryRepository extends JpaRepository<PickDropHistory, Long> {

    List<PickDropHistory> findByUserId(Long id);
}
