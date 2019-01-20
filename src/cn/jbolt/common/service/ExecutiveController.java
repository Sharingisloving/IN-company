package cn.jbolt.common.service;

import java.util.List;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;

import cn.jbolt.common.model.Companybusiness;
import cn.jbolt.common.model.Keyexecutive;

public class ExecutiveController extends Controller {
	public void search(){
		int totalPage;		//总共的页数
		int currentPage=1;		//当前页号
		Integer pageNumber=getParaToInt("pageNumber");
		if(pageNumber==null){
			pageNumber=1;
		}
		String ExecutiveName = getPara("HumanName");//获取输入框数据
		Page<Record> list=Companybusiness.dao.findByExecutiveName(pageNumber,ExecutiveName);
		totalPage=list.getTotalPage();  
		System.out.println(list.getTotalRow());
		currentPage=pageNumber;
		setAttr("totalPage", totalPage);
		setAttr("pageNumber", list.getPageNumber());
		setAttr("Executive", list.getList());
		setAttr("ExecutiveName", ExecutiveName);
		render("/view/Executive_list.html");
	}
	public void Page() {
		String action=new String(getPara("action").toString());
		char mode=action.charAt(0);
		int currentPage=getParaToInt("pageNumber") ;
		int totalPage=getParaToInt("totalPage") ;
		String ExecutiveName=new String(getPara("ExecutiveName").toString());
		Integer pageNumber=getParaToInt("pageNumber2");
		if(mode=='p')//上一页
		{
			if(currentPage==1){	
				currentPage=1;	//当前页是第一页
				
			}else{
				currentPage-=1;			//页数加1
			}
		}
		else if(mode=='n')//下一页
		{
			if(currentPage==totalPage){	
				currentPage=totalPage;	//当前页是最后一页
			}else{
				currentPage+=1;			//页数加1
			}
		}
		else //跳转
		{
			if(pageNumber!=null){
				currentPage=pageNumber;
			}
		}
		Page<Record> list=Companybusiness.dao.findByCompanyName(currentPage,ExecutiveName);
		setAttr("totalPage", totalPage);
		setAttr("pageNumber", list.getPageNumber());
		setAttr("Executive", list.getList());
		setAttr("ExecutiveName", ExecutiveName);
		render("/view/Executive_list.html");
	}
	
	public void RelativeEx() {
		int CompanyId = getParaToInt("CompanyId");//获取输入框数据
		String name=getPara("CompanyName");
		List<Record> list=Keyexecutive.dao.findByExecutiveName(CompanyId);
		setAttr("RelativeEx", list);		
		setAttr("CompanyName", name);
		render("/view/Executive_RelativeEx.html");
	}
}
