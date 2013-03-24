package org.objectquery.generic.domain;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class BoxedPrimitiveDomain {

	private Boolean bool;
	private Byte bytee;
	private Character chare;
	private Integer inte;
	private Short shorte;
	private Long longe;
	private Float floate;
	private Double doublee;
	private BigDecimal bigDecimal;
	private BigInteger bigInteger;
	private AtomicInteger atomicInteger;
	private AtomicLong atomicLong;
	private AtomicBoolean atomicBoolean;
	private Date date;

	public Boolean getBool() {
		return bool;
	}

	public void setBool(Boolean bool) {
		this.bool = bool;
	}

	public Byte getBytee() {
		return bytee;
	}

	public void setBytee(Byte bytee) {
		this.bytee = bytee;
	}

	public Character getChare() {
		return chare;
	}

	public void setChare(Character chare) {
		this.chare = chare;
	}

	public Integer getInte() {
		return inte;
	}

	public void setInte(Integer inte) {
		this.inte = inte;
	}

	public Short getShorte() {
		return shorte;
	}

	public void setShorte(Short shorte) {
		this.shorte = shorte;
	}

	public Long getLonge() {
		return longe;
	}

	public void setLonge(Long longe) {
		this.longe = longe;
	}

	public Float getFloate() {
		return floate;
	}

	public void setFloate(Float floate) {
		this.floate = floate;
	}

	public Double getDoublee() {
		return doublee;
	}

	public void setDoublee(Double doublee) {
		this.doublee = doublee;
	}

	public BigDecimal getBigDecimal() {
		return bigDecimal;
	}

	public void setBigDecimal(BigDecimal bigDecimal) {
		this.bigDecimal = bigDecimal;
	}

	public BigInteger getBigInteger() {
		return bigInteger;
	}

	public void setBigInteger(BigInteger bigInteger) {
		this.bigInteger = bigInteger;
	}

	public AtomicInteger getAtomicInteger() {
		return atomicInteger;
	}

	public void setAtomicInteger(AtomicInteger atomicInteger) {
		this.atomicInteger = atomicInteger;
	}

	public AtomicLong getAtomicLong() {
		return atomicLong;
	}

	public void setAtomicLong(AtomicLong atomicLong) {
		this.atomicLong = atomicLong;
	}

	public AtomicBoolean getAtomicBoolean() {
		return atomicBoolean;
	}

	public void setAtomicBoolean(AtomicBoolean atomicBoolean) {
		this.atomicBoolean = atomicBoolean;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}
