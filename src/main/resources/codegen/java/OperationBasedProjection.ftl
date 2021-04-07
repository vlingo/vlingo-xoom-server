package ${packageName};

<#list imports as import>
import ${import.qualifiedClassName};
</#list>

import io.vlingo.lattice.model.projection.Projectable;
import io.vlingo.lattice.model.projection.StateStoreProjectionActor;
import io.vlingo.symbio.store.state.StateStore;

/**
 * See
 * <a href="https://docs.vlingo.io/vlingo-lattice/projections#implementing-with-the-statestoreprojectionactor">
 *   Implementing With the StateStoreProjectionActor
 * </a>
 */
public class ${projectionName} extends StateStoreProjectionActor<${dataName}> {
  private String becauseOf;

  public ${projectionName}() {
    this(${storeProviderName}.instance().store);
  }

  public ${projectionName}(final StateStore stateStore) {
    super(stateStore);
  }

  @Override
  protected ${dataName} currentDataFor(Projectable projectable) {
    becauseOf = projectable.becauseOf()[0];
    final ${stateName} state = projectable.object();
    return ${dataName}.from(state);
  }

  @Override
  protected ${dataName} merge(final ${dataName} previousData, final int previousVersion, final ${dataName} currentData, final int currentVersion) {
    if (previousVersion == currentVersion) return currentData;

    ${dataName} merged = previousData;

    switch (${projectionSourceTypesName}.valueOf(becauseOf)) {
      <#list sources as source>
      case ${source.name}: {
        merged = ${source.dataObjectName}.from(${source.mergeParameters});
        break;
      }

      </#list>
      default:
        logger().warn("Operation of type " + becauseOf + " was not matched.");
        break;
    }

    return merged;
  }
}
