package soccer.deploy.entry.service;

import java.util.List;

import soccer.deploy.entry.entity.Entry;

public interface EntryService {
	//�ֽ� ��ġ ��Ʈ�� ��������
	public List<Entry> findEntryRecentMatch(Long id);
	
	
}
