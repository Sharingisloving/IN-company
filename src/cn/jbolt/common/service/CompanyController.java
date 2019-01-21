package cn.jbolt.common.service;

import com.jfinal.core.ActionKey;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;

import cn.jbolt.common.model.Companybusiness;


public class CompanyController extends Controller {
	public void index() {
    	render("/view/main.jsp");
    }
	
	public void search1(){
		Integer pageNumber=getParaToInt("pageNumber");
		if(pageNumber==null){
			pageNumber=1;
		}
		String company = getPara("InputName");//获取输入框数据
		Page<Record> list=Companybusiness.dao.findByCompanyName(pageNumber,company); 
		setAttr("mode", "1");								//1为按名字搜索企业
		setAttr("totolRecord", list.getTotalRow());
		setAttr("totalPage", list.getTotalPage());
		setAttr("pageNumber", list.getPageNumber());
		setAttr("Company", list.getList());
		setAttr("input", company);
		render("/view/company_list.jsp");
	}

	public void PrePage(){

		Integer pageNumber=getParaToInt("pageNumber");
		String mode=getPara("mode");
		if(pageNumber==1){	
			pageNumber=1;	//当前页是第一页
		}else{
			pageNumber-=1;			//页数加1
		}
		if(mode.equals("1")){
			String company = getPara("input");//获取输入框数据
			Page<Record> list=Companybusiness.dao.findByCompanyName(pageNumber,company);
			setAttr("mode","1");	
			setAttr("totolRecord", list.getTotalRow());
			setAttr("totalPage", list.getTotalPage());
			setAttr("pageNumber", list.getPageNumber());
			setAttr("Company", list.getList());
			render("/view/company_list.jsp");
		}else if(mode.equals("2")){
			String Industry=getPara("Industry");
			Page<Record> list=Companybusiness.dao.findBySimilarIndustry(pageNumber, Industry);
			setAttr("mode","2");	
			setAttr("Industry",Industry);
			setAttr("totolRecord", list.getTotalRow());
			setAttr("totalPage", list.getTotalPage());
			setAttr("pageNumber", list.getPageNumber());
			setAttr("Company", list.getList());
			render("/view/company_list.jsp");
		}else if(mode.equals("3")){
			String KeyExecutives=getPara("KeyExecutive");
			Page<Record> list=Companybusiness.dao.findBySameExecutives(pageNumber,KeyExecutives);
			setAttr("mode","3");	
			setAttr("totolRecord", list.getTotalRow());
			setAttr("totalPage", list.getTotalPage());
			setAttr("pageNumber", list.getPageNumber());
			setAttr("Company", list.getList());
			render("/view/company_list.jsp");
		}
	}
	public void NextPage(){
		
		String mode=getPara("mode");
		Integer pageNumber=getParaToInt("pageNumber");
		int totalPage=getParaToInt("totalPage");
		if(pageNumber==totalPage){	
			pageNumber=totalPage;	//当前页是最后一页
		}else{
			pageNumber+=1;			//页数加1
		}
		if(mode.equals("1")){
			String company = getPara("input");//获取输入框数据
			Page<Record> list=Companybusiness.dao.findByCompanyName(pageNumber,company);
			setAttr("mode","1");	
			setAttr("totolRecord", list.getTotalRow());
			setAttr("totalPage", list.getTotalPage());
			setAttr("pageNumber", list.getPageNumber());
			setAttr("Company", list.getList());
			render("/view/company_list.jsp");
		}else if(mode.equals("2")){
			String Industry=getPara("Industry");
			Page<Record> list=Companybusiness.dao.findBySimilarIndustry(pageNumber, Industry);
			setAttr("mode","2");	
			setAttr("Industry",Industry);
			setAttr("totolRecord", list.getTotalRow());
			setAttr("totalPage", list.getTotalPage());
			setAttr("pageNumber", list.getPageNumber());
			setAttr("Company", list.getList());
			render("/view/company_list.jsp");
		}else if(mode.equals("3")){
			String KeyExecutives=getPara("KeyExecutive");
			Page<Record> list=Companybusiness.dao.findBySameExecutives(pageNumber,KeyExecutives);
			setAttr("mode","3");	
			setAttr("totolRecord", list.getTotalRow());
			setAttr("totalPage", list.getTotalPage());
			setAttr("pageNumber", list.getPageNumber());
			setAttr("Company", list.getList());
			render("/view/company_list.jsp");
		}
		
	}
	public void JumpPage(){
		String mode=getPara("mode");
		System.out.println(mode);
		Integer pageNumber=getParaToInt("pageNumber");
		if(pageNumber==null){
			pageNumber=1;
		}
		if(mode.equals("1")){
			String company = getPara("input");//获取输入框数据
			Page<Record> list=Companybusiness.dao.findByCompanyName(pageNumber,company);
			setAttr("mode","1");	
			setAttr("totolRecord", list.getTotalRow());
			setAttr("totalPage", list.getTotalPage());
			setAttr("pageNumber", list.getPageNumber());
			setAttr("Company", list.getList());
			render("/view/company_list.jsp");
		}else if(mode.equals("2")){
			String Industry=getPara("Industry");
			Page<Record> list=Companybusiness.dao.findBySimilarIndustry(pageNumber, Industry);
			setAttr("mode","2");	
			setAttr("Industry",Industry);
			setAttr("totolRecord", list.getTotalRow());
			setAttr("totalPage", list.getTotalPage());
			setAttr("pageNumber", list.getPageNumber());
			setAttr("Company", list.getList());
			render("/view/company_list.jsp");
		}else if(mode.equals("3")){
			String KeyExecutives=getPara("KeyExecutive");
			Page<Record> list=Companybusiness.dao.findBySameExecutives(pageNumber,KeyExecutives);
			setAttr("mode","3");	
			setAttr("totolRecord", list.getTotalRow());
			setAttr("totalPage", list.getTotalPage());
			setAttr("pageNumber", list.getPageNumber());
			setAttr("Company", list.getList());
			render("/view/company_list.jsp");
		}
		
	}
	@ActionKey("detail") 
	public void CompanyDetail(){
		String CompanyName=getPara("company");
		Record result=Companybusiness.dao.findall(CompanyName);
		int CompanyId=result.get("CompanyId");
		Page<Record> list=Companybusiness.dao.findExecutives(1,CompanyId);
		setAttr("CompanyName", CompanyName);
		setAttr("Company", list.getList());
		setAttr("StateCounty", result.get("StateCounty"));
		setAttr("City", result.get("City"));
		setAttr("Industry", result.getStr("Industry"));
		setAttr("NumberOfEmployees", result.get("NumberOfEmployees"));
		setAttr("LegalForm", result.get("LegalForm"));
		setAttr("FinancialAuditors", result.get("FinancialAuditors"));
		setAttr("ListedUnlisted", result.get("ListedUnlisted"));
		setAttr("OperationalStatus", result.get("OperationalStatus"));
		setAttr("ISIN", result.get("ISIN"));
		setAttr("NationalFiscalIDs", result.get("NationalFiscalIDs"));
		setAttr("Shareholders", result.get("Shareholders"));
		setAttr("MainProducts", result.get("MainProducts"));
		setAttr("PostalCode", result.get("PostalCode"));
		setAttr("Address", result.get("Address"));
		setAttr("Phone", result.get("Phone"));
		setAttr("Fax", result.get("Fax"));
		setAttr("Email", result.get("Email"));
		setAttr("Website", result.get("Website"));
		setAttr("AddressType", result.get("AddressType"));
		setAttr("BusinessDescriptionProducts", result.get("BusinessDescriptionProducts"));
		setAttr("Import", result.get("Import"));
		setAttr("Export", result.get("Export"));
		setAttr("Source", result.get("Source"));
		setAttr("IncorporationDate", result.get("IncorporationDate"));
		setAttr("EMISID", result.get("EMISID"));
		setAttr("Totaloperatingrevenue", result.get("Totaloperatingrevenue"));
		setAttr("FiscalYear", result.get("FiscalYear"));
		setAttr("Audited", result.get("Audited"));
		setAttr("Consolidated", result.get("Consolidated"));
		setAttr("F35", result.get("F35"));
		render("/view/company_detail.jsp");
	}
	@ActionKey("similar") 
	public void SimilarIndustry(){
		String Industry=getPara("Industry");
		Page<Record> list=Companybusiness.dao.findBySimilarIndustry(1, Industry);	   
		//currentPage=pageNumber;
		setAttr("mode","2");											//2为相似业务推荐
		setAttr("Industry",Industry);
		setAttr("totolRecord", list.getTotalRow());
		setAttr("totalPage", list.getTotalPage());
		setAttr("pageNumber", list.getPageNumber());
		setAttr("Company", list.getList());
		render("/view/company_list.jsp");
	}
	@ActionKey("relative")
	public void RelativeCompany(){
		String KeyExecutives=getPara("KeyExecutive");
		Page<Record> list=Companybusiness.dao.findBySameExecutives(1,KeyExecutives);	   
		setAttr("mode", "3");									//3为关联企业推荐
		setAttr("totolRecord", list.getTotalRow());
		setAttr("totalPage", list.getTotalPage());
		setAttr("pageNumber", list.getPageNumber());
		setAttr("Company", list.getList());
		render("/view/company_list.jsp");
		
	}
}
