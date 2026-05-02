package com.constructionmanager.manager.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "process_globa_requisition")
public class ProcessGlobalRequisition {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Integer id;

	@OneToOne(mappedBy = "processGlobalRequisition")
	private ProcessRequisition processRequisition;

	@OneToMany(mappedBy = "processGlobalRequisition", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	@JsonIgnore
	private List<ProcessRequisitionItem> processRequisitionItems = new ArrayList<>();

	public ProcessGlobalRequisition() {
	}

	public ProcessGlobalRequisition(ProcessRequisition processRequisition,
			List<ProcessRequisitionItem> processRequisitionItems) {
		this.processRequisition = processRequisition;
		this.processRequisitionItems = processRequisitionItems;
	}

	public ProcessRequisition getProcessRequisition() {
		return processRequisition;
	}

	public void setProcessReuqisition(ProcessRequisition processRequisition) {
		this.processRequisition = processRequisition;
	}

	public List<ProcessRequisitionItem> getProcessRequisitionItems() {
		return processRequisitionItems;
	}

	public void setProcessRequisitionItems(List<ProcessRequisitionItem> processRequisitionItems) {
		this.processRequisitionItems = processRequisitionItems;
	}
}
