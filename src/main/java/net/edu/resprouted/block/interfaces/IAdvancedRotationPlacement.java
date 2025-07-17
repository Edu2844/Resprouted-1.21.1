package net.edu.resprouted.block.interfaces;

import net.minecraft.block.BlockState;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.util.math.Direction;
/**
 * Interfaz pars sistema avanzado de rotación al ser colocado el bloque de Diagonal Clay Wall,
 * con lógica especial cuando se coloca desde arriba o abajo (eje Y).
 */

public interface IAdvancedRotationPlacement {
    /**
     * Calcula el estado del bloque con la rotación adecuada basada en cómo el jugador lo coloca.
     *
     * @param defaultState El estado por defecto del bloque
     * @param facingProperty La propiedad de dirección que controla la orientación del bloque
     * @param context El contexto de colocación que contiene información sobre cómo se está colocando el bloque
     * @return El estado del bloque con la rotación aplicada
     *
     * @implNote
     * La lógica especial se aplica cuando:
     * 1. Se coloca desde arriba/abajo (eje Y): Determina la dirección basada en la posición relativa del click
     * 2. Se coloca desde los lados: Usa la dirección opuesta a la cara clickeada
     */
    default BlockState getStateForAdvancedRotationPlacement(BlockState defaultState, DirectionProperty facingProperty, ItemPlacementContext context) {
        Direction facing = context.getSide();
        double hitX = context.getHitPos().x - context.getBlockPos().getX();
        double hitZ = context.getHitPos().z - context.getBlockPos().getZ();
        if (facing.getAxis() == Direction.Axis.Y) {
            double hitXFromCenter = hitX - 0.5;
            double hitZFromCenter = hitZ - 0.5;
            if (Math.max(Math.abs(hitXFromCenter), Math.abs(hitZFromCenter)) == Math.abs(hitXFromCenter)) {
                return defaultState.with(facingProperty, (hitXFromCenter > 0) ? Direction.EAST : Direction.WEST);
            } else {
                return defaultState.with(facingProperty, (hitZFromCenter > 0) ? Direction.SOUTH : Direction.NORTH);
            }
        }
        return defaultState.with(facingProperty, facing.getOpposite());
    }
}
