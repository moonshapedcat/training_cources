package ru.stqa.pft.mantis.model;

import com.google.common.collect.ForwardingSet;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
public class MantisUsers extends ForwardingSet<UserData> {

  private Set<UserData> delegateOb;

  public MantisUsers(List<biz.futureware.mantis.rpc.soap.client.UserData> users) {
    this.delegateOb = new HashSet<>();
  }

  public MantisUsers(Collection<UserData> users) {
    this.delegateOb = new HashSet<>(users);
  }

  @Override

  protected Set delegate() {
    return delegateOb;
  }}

