package com.suwadi.web.bindingInitializer;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebBindingInitializer;
import org.springframework.web.context.request.WebRequest;

import com.suwadi.domain.Beneficiary;
import com.suwadi.domain.Designation;
import com.suwadi.domain.District;
import com.suwadi.domain.Division;
import com.suwadi.domain.Expense;
import com.suwadi.domain.ExpenseAccount;
import com.suwadi.domain.FamilyRelationship;
import com.suwadi.domain.GNDivision;
import com.suwadi.domain.Gender;
import com.suwadi.domain.Group;
import com.suwadi.domain.MaritalStatus;
import com.suwadi.domain.Product;
import com.suwadi.domain.SalaryType;
import com.suwadi.domain.Society;
import com.suwadi.domain.SpecialIdentification;
import com.suwadi.domain.Vendor;
import com.suwadi.service.BeneficiaryService;
import com.suwadi.service.DesignationService;
import com.suwadi.service.DistrictService;
import com.suwadi.service.DivisionService;
import com.suwadi.service.ExpenseAccountService;
import com.suwadi.service.ExpenseService;
import com.suwadi.service.FamilyRelationshipService;
import com.suwadi.service.GNDivisionService;
import com.suwadi.service.GroupService;
import com.suwadi.service.ProductService;
import com.suwadi.service.RoleService;
import com.suwadi.service.SocietyService;
import com.suwadi.service.VendorService;
import com.suwadi.web.propertyEditor.BeneficiaryPropertyEditor;
import com.suwadi.web.propertyEditor.DesignationPropertyEditor;
import com.suwadi.web.propertyEditor.DistrictPropertyEditor;
import com.suwadi.web.propertyEditor.DivisionPropertyEditor;
import com.suwadi.web.propertyEditor.ExpenseAccountPropertyEditor;
import com.suwadi.web.propertyEditor.ExpensePropertyEditor;
import com.suwadi.web.propertyEditor.FamilyRelationshipPropertyEditor;
import com.suwadi.web.propertyEditor.GNDivisionPropertyEditor;
import com.suwadi.web.propertyEditor.GenderPropertyEditor;
import com.suwadi.web.propertyEditor.GroupPropertyEditor;
import com.suwadi.web.propertyEditor.MaritalStatusPropertyEditor;
import com.suwadi.web.propertyEditor.ProductPropertyEditor;
import com.suwadi.web.propertyEditor.RolePropertyEditor;
import com.suwadi.web.propertyEditor.SalaryTypePropertyEditor;
import com.suwadi.web.propertyEditor.SocietyPropertyEditor;
import com.suwadi.web.propertyEditor.SpecialIdentificationPropertyEditor;
import com.suwadi.web.propertyEditor.VendorPropertyEditor;

public class BindingInitializer implements WebBindingInitializer {
	private RoleService roleService;
	private DistrictService districtService;
	private DivisionService divisionService;
	private DesignationService designationService;
	private BeneficiaryService beneficiaryService;
	private GNDivisionService gnDivisionService;
	private SocietyService societyService;
	private GroupService groupService;
	private FamilyRelationshipService familyRelationshipService;
	private VendorService vendorService;
	private ExpenseAccountService expenseAccountService;
	private ProductService productService;
	private ExpenseService expenseService;

	@Autowired
	public BindingInitializer(RoleService roleService,
			DistrictService districtService, DivisionService divisionService,
			DesignationService designationService,
			BeneficiaryService beneficiaryService,
			GNDivisionService gnDivisionService, SocietyService societyService,
			GroupService groupService,
			FamilyRelationshipService familyRelationshipService,
			VendorService vendorService,
			ExpenseAccountService expenseAccountService,
			ProductService productService, ExpenseService expenseService) {
		this.roleService = roleService;
		this.districtService = districtService;
		this.divisionService = divisionService;
		this.designationService = designationService;
		this.beneficiaryService = beneficiaryService;
		this.gnDivisionService = gnDivisionService;
		this.societyService = societyService;
		this.groupService = groupService;
		this.familyRelationshipService = familyRelationshipService;
		this.vendorService = vendorService;
		this.expenseAccountService = expenseAccountService;
		this.productService = productService;
		this.expenseService = expenseService;
	}

	public void initBinder(WebDataBinder binder, WebRequest request) {
		binder.registerCustomEditor(List.class, "roles",
				new RolePropertyEditor(List.class, roleService));
		binder.registerCustomEditor(District.class, new DistrictPropertyEditor(
				districtService));
		binder.registerCustomEditor(Division.class, new DivisionPropertyEditor(
				divisionService));
		binder.registerCustomEditor(Designation.class,
				new DesignationPropertyEditor(designationService));
		binder.registerCustomEditor(Beneficiary.class,
				new BeneficiaryPropertyEditor(beneficiaryService));
		binder.registerCustomEditor(GNDivision.class,
				new GNDivisionPropertyEditor(gnDivisionService));
		binder.registerCustomEditor(Society.class, new SocietyPropertyEditor(
				societyService));
		binder.registerCustomEditor(Group.class, new GroupPropertyEditor(
				groupService));

		// date property editor
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, true));
		binder.registerCustomEditor(Gender.class, new GenderPropertyEditor());
		binder.registerCustomEditor(MaritalStatus.class,
				new MaritalStatusPropertyEditor());
		binder.registerCustomEditor(SpecialIdentification.class,
				new SpecialIdentificationPropertyEditor());
		binder.registerCustomEditor(FamilyRelationship.class,
				new FamilyRelationshipPropertyEditor(familyRelationshipService));
		binder.registerCustomEditor(Vendor.class, new VendorPropertyEditor(
				this.vendorService));
		binder.registerCustomEditor(ExpenseAccount.class,
				new ExpenseAccountPropertyEditor(this.expenseAccountService));
		binder.registerCustomEditor(Product.class, new ProductPropertyEditor(
				this.productService));
		binder.registerCustomEditor(Expense.class, new ExpensePropertyEditor(
				this.expenseService));
		binder.registerCustomEditor(SalaryType.class,
				new SalaryTypePropertyEditor());
	}
}
