package com.flypaas.service.impl.resource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flypaas.dao.TbRsCityMapper;
import com.flypaas.dao.TbRsRTPPMapper;
import com.flypaas.model.TbRsRTPP;
import com.flypaas.service.resource.ResourceService;
import com.flypaas.util.DateUtil;
import com.flypaas.util.PageContainer;

@Service("resourceServiceImpl")
public class ResourceServiceImpl implements ResourceService{
	@Autowired
	private TbRsRTPPMapper tbRsRtppMapper;
	
	@Autowired
	private TbRsCityMapper tbRsCityMapper;
	
	
	@Override
	public int deleteByPrimaryKey(Integer rtppSid) {
		
		return tbRsRtppMapper.deleteByPrimaryKey(rtppSid);
	}

	@Override
	public int insert(TbRsRTPP record) {
		
		return tbRsRtppMapper.insert(record);
	}

	@Override
	public int insertSelective(TbRsRTPP record) {
		int continentId = tbRsCityMapper.queryContinentByCountry(record.getCountry());
		int areaId = tbRsCityMapper.queryAreaByCity(record.getZone());
		record.setContinent(continentId);
		record.setRegion(areaId);
		int temp = tbRsRtppMapper.insertSelective(record);
		return temp;
	}

	@Override
	public TbRsRTPP selectByPrimaryKey(Integer rtppSid) {
		
		return tbRsRtppMapper.selectByPrimaryKey(rtppSid);
	}

	@Override
	public int updateByPrimaryKeySelective(TbRsRTPP record) {
		int continentId = tbRsCityMapper.queryContinentByCountry(record.getCountry());
		int region = tbRsCityMapper.queryAreaByCity(record.getZone());
		record.setContinent(continentId);
		record.setRegion(region);
		int temp = tbRsRtppMapper.updateByPrimaryKey(record);
		return temp;
	}

	@Override
	public int updateByPrimaryKey(TbRsRTPP record) {
		
		return tbRsRtppMapper.updateByPrimaryKey(record);
	}

	@Override
	public PageContainer queryAllResourceFenYe(String netSid, String netArea,int page,String ipOrName,String dateMin,String dateMax) {
		Map<String,Object> para = new HashMap<String,Object>();
		para.put("netSid", netSid);
		para.put("ip", ipOrName);
		para.put("name", ipOrName);
		para.put("dateMin", dateMin);
		para.put("dateMax", DateUtil.add(dateMax, 1));
//		para.put("netArea", netArea);
		int total = tbRsRtppMapper.queryResourceCount(para);
		PageContainer pageContainer = new PageContainer();
		para = pageContainer.createParameter(para, page, total);
		List<Map<String, String>> result = tbRsRtppMapper.queryAllResourceFenYe(para);
		pageContainer.setResultMap(result);
		return pageContainer;
	}

	@Override
	public int queryResourceCount(String netSid,String ipOrName,String dateMin,String dateMax) {
		Map<String,Object> para = new HashMap<String,Object>();
		para.put("netSid", netSid);
		para.put("dateMin", dateMin);
		para.put("dateMax", dateMax);
		int count = tbRsRtppMapper.queryResourceCount(para);
		return count;
	}

	/**
	 * 0   删除失败   1  服务器出错   2  删除成功
	 */
	@Override
	public int batchDelResource(String checkedId) {
		int temp = tbRsRtppMapper.batchDelResource(checkedId.split(","));
		return temp;
		
	}

	@Override
	public TbRsRTPP queryResourceByIpOrName(TbRsRTPP tbRsRTPP1) {

		return tbRsRtppMapper.queryResourceByIpOrName(tbRsRTPP1);
	}

	@Override
	public TbRsRTPP checkIp(String ip) {
		TbRsRTPP tbRsRTPP = null;
		List<TbRsRTPP> RTPPList = tbRsRtppMapper.checkIp(ip);
		if(RTPPList != null && !RTPPList.isEmpty()){
			tbRsRTPP = RTPPList.get(0);
		}
		return tbRsRTPP;
	}
	
	@Override
	public List<TbRsRTPP> getRTPPListByIp(String ip) {
		List<TbRsRTPP> RTPPList = tbRsRtppMapper.checkIp(ip);
		return RTPPList;
	}
	

	@Override
	public List<TbRsRTPP> queryAllResource(String netSid) {
		
		return tbRsRtppMapper.queryAllResource(netSid);
	}


}
