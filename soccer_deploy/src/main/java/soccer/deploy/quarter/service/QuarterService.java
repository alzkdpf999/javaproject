package soccer.deploy.quarter.service;

import java.util.List;

import soccer.deploy.quarter.entity.Quarter;

public interface QuarterService {
	//�ֽ� ��� �������� ��������
	List<Quarter> findQuarterRecentMatch(Long id);
}
