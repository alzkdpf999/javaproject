package soccer.deploy.match.service;

import soccer.deploy.match.entity.Match;

public interface MatchService {
	//�ֽ� ��ġid���� ���������
	public Long findRecentMatchNum();
	
	//�ֽ� ���
	public Match findeRecentMatch(Long id);
}
