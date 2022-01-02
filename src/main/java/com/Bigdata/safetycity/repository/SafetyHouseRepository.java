package com.Bigdata.safetycity.repository;

import com.Bigdata.safetycity.model.Count;
import com.Bigdata.safetycity.model.datas.Cctv;
import com.Bigdata.safetycity.model.datas.SafetyHouse;
import com.Bigdata.safetycity.model.datas.StreetLamp;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SafetyHouseRepository extends JpaRepository<SafetyHouse, Long> {
    @Query(value = "SELECT " +
            "safetyhouse.area as name, COUNT(*) AS cnt " +
            "FROM safetyhouse " +
            "group by safetyhouse.area " +
            "order by cnt desc " +
            "limit 5",
            nativeQuery = true
    )
    List<Count> findTop5Safetyhouse();
    Page<SafetyHouse> findByArea(Pageable pageable, String area);
    @Query(value = "SELECT * " +
            "FROM safetycity.safetyhouse " +
            "where wgs_x > ?1 and wgs_x < ?2 " +
            "and wgs_y > ?3 and wgs_y < ?4",
            nativeQuery = true
    )
    List<SafetyHouse> findSafetyHouseBylatlng(double minX, double maxX, double minY, double maxY);
}
